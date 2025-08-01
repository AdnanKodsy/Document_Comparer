package com.adnan.documentcomparer.MatchingRatio;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WordsMatchCalculater implements MatchCalculater {

    @Override
    public double calculateScore(List<String> fileA, List<String> otherFile) {
        if (fileA.isEmpty() && otherFile.isEmpty()) return 0.0;
        long matchCount = otherFile.stream().filter(fileA::contains).count();
        int unionSize = fileA.size() + (int) otherFile.stream().filter(word -> !fileA.contains(word)).count();

        return (unionSize == 0) ? 0.0 : ((double) matchCount / unionSize) * 100;
    }
}
