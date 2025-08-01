package com.adnan.documentcomparer.WordExtractor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface WordExtractor {

    List<String> extractWords(Path filePath) throws IOException;
}
