package com.adnan.documentcomparer.MatchingRatio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class WordsMatchCalculater implements MatchCalculater {

    @Override
    public double calculateScore(Set<String> fileA, Set<String> otherFile) {
        long matchCount = otherFile.stream().filter(fileA::contains).count();
        Set<String> union = new HashSet<>(fileA);
        union.addAll(otherFile);
        return (fileA.isEmpty()) ? 0.0 : ((double) matchCount / union.size()) * 100;
    }
}
