package com.adnan.documentcomparer.WordExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class DocumentWordExtractor implements WordExtractor {

    @Override
    public Set<String> extractWords(Path filePath) throws IOException {
        return Files.lines(filePath)
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .map(word -> word.replaceAll("[^A-Za-z]", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toSet());
    }
}
