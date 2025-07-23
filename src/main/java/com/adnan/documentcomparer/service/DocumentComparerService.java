package com.adnan.documentcomparer.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.adnan.documentcomparer.MatchingRatio.MatchCalculater;
import com.adnan.documentcomparer.WordExtractor.WordExtractor;
import com.adnan.documentcomparer.config.DocumentComparerProperties;

@Service
public class DocumentComparerService {

    private final WordExtractor extractor;
    private final MatchCalculater matchCalculater;
    private final DocumentComparerProperties properties;

    public DocumentComparerService(WordExtractor extractor, MatchCalculater matchCalculater, DocumentComparerProperties properties) {
        this.extractor = extractor;
        this.matchCalculater = matchCalculater;
        this.properties = properties;
    }

    public Map<String, Double> matchScores() throws IOException {
        Set<String> wordsInA = extractor.extractWords(Paths.get(properties.getBaseFilePath()));
        Map<String, Double> results = new HashMap<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(properties.getInputFilesDir()))) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    Set<String> otherWords = extractor.extractWords(path);
                    double score = matchCalculater.calculateScore(wordsInA, otherWords);
                    results.put(path.getFileName().toString(), score);
                }
            }
        }

        return results;
    }
}
