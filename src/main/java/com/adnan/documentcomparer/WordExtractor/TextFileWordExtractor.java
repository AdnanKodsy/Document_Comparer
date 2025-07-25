package com.adnan.documentcomparer.WordExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("txt")
public class TextFileWordExtractor implements WordExtractor {

    private static final Logger logger = LoggerFactory.getLogger(TextFileWordExtractor.class);

    @Override
    public Set<String> extractWords(Path filePath) {
        try {
            return Files.lines(filePath)
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^A-Za-z]", "").toLowerCase())
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
            return java.util.Collections.emptySet();
        }
    }
}
