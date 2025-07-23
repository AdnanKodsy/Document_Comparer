package com.adnan.documentcomparer.WordExtractor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public interface WordExtractor {

    Set<String> extractWords(Path filePath) throws IOException;
}
