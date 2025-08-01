package com.adnan.documentcomparer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adnan.documentcomparer.service.DocumentComparerService;

@RestController
@RequestMapping("/api/document-comparer")
public class DocumentComparerController {

    private final DocumentComparerService service;

    @Autowired
    public DocumentComparerController(DocumentComparerService service) {
        this.service = service;
    }

    @GetMapping("/compare")
    public Map<String, Double> compareDocuments() {
        return service.matchScores();
    }
}