package com.adnan.documentcomparer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "file")
@Getter
@Setter

public class DocumentComparerProperties {

    private String baseFilePath;
    private String inputFilesDir;
}
