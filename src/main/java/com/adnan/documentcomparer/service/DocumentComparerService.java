package com.adnan.documentcomparer.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adnan.documentcomparer.MatchingRatio.MatchCalculater;
import com.adnan.documentcomparer.WordExtractor.WordExtractor;
import com.adnan.documentcomparer.config.DocumentComparerProperties;

@Service
public class DocumentComparerService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentComparerService.class);
    private final WordExtractor extractor;
    private final MatchCalculater matchCalculater;
    private final DocumentComparerProperties properties;

    public DocumentComparerService(WordExtractor extractor, MatchCalculater matchCalculater, DocumentComparerProperties properties) {
        this.extractor = extractor;
        this.matchCalculater = matchCalculater;
        this.properties = properties;
    }

    public Map<String, Double> matchScores() {
        Map<String, Double> results = new HashMap<>();
        List<String> wordsInA;
        try {
            wordsInA = extractor.extractWords(Paths.get(properties.getBaseFilePath()));
        } catch (IOException e) {
            logger.error("Failed to extract words from base file: {}", properties.getBaseFilePath(), e);
            return results;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(properties.getInputFilesDir()))) {
            stream.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    executorService.submit(() -> {
                        try {
                            List<String> otherWords = extractor.extractWords(path);
                            double score = matchCalculater.calculateScore(wordsInA, otherWords);
                            synchronized (results) {
                                results.put(path.getFileName().toString(), score);
                            }
                        } catch (IOException e) {
                            logger.error("Failed to extract words from file: {}", path, e);
                        }
                    });
                }
            });
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (IOException e) {
            logger.error("Failed to read input files directory: {}", properties.getInputFilesDir(), e);
        } catch (InterruptedException e) {
            logger.error("Thread execution was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return new TreeMap<>(results);
    }
}
