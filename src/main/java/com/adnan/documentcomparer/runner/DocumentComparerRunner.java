package com.adnan.documentcomparer.runner;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.adnan.documentcomparer.service.DocumentComparerService;

@Component
public class DocumentComparerRunner implements CommandLineRunner {

    private final DocumentComparerService service;
    private static final Logger logger = LoggerFactory.getLogger(DocumentComparerRunner.class);

    public DocumentComparerRunner(DocumentComparerService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        try {
            Map<String, Double> results = service.matchScores();

            results.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .forEach(entry -> System.out.printf("File: %-20s Score: %.2f%%%n", entry.getKey(), entry.getValue()));
        } catch (Exception e) {
            logger.error("Error occurred during document comparison", e);
            System.err.println("An error occurred while comparing documents. Please check the logs for details.");
        }
    }
}
