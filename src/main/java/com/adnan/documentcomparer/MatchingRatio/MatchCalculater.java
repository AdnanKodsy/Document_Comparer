package com.adnan.documentcomparer.MatchingRatio;

import java.util.Set;

public interface MatchCalculater {

    double calculateScore(Set<String> fileA, Set<String> otherFile);
}
