package com.adnan.documentcomparer.runner;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.adnan.documentcomparer.service.DocumentComparerService;

@Component
public class DocumentComparerRunner implements CommandLineRunner {

    private final DocumentComparerService service;

    public DocumentComparerRunner(DocumentComparerService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Double> results = service.matchScores();

        results.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("File: %-20s Score: %.2f%%%n", entry.getKey(), entry.getValue()));
    }
}
