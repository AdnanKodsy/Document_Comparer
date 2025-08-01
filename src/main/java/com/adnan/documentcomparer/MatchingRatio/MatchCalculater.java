package com.adnan.documentcomparer.MatchingRatio;

import java.util.List;

public interface MatchCalculater {

    double calculateScore(List<String> fileA, List<String> otherFile);
}
