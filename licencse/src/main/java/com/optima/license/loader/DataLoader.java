package com.optima.license.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optima.license.entity.License;
import com.optima.license.repository.LicenseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
@Profile({"local", "dev", "test"})
public class DataLoader implements CommandLineRunner {

    private final ObjectMapper mapper;
    private final LicenseRepository repository;

    public static final String LICENSE_DATA_FILE = "license-data.json";
    public static final String ORGANIZATION_DATA_FILE = "organization-data.json";

    public void run(String... args) throws IOException {
        log.info("Loading data...");
        List<License> licenseList = mapper.readValue(
                getFilePath(LICENSE_DATA_FILE)
                , new TypeReference<>() {
                });
        repository.saveAll(licenseList);
        log.info("Data loaded successfully, {} licenses added", licenseList.size());
    }

    private File getFilePath(String fileName) {
        return switch (fileName) {
            case LICENSE_DATA_FILE -> new File("src/main/resources/data/license-data.json");
            case ORGANIZATION_DATA_FILE -> new File("src/main/resources/data/organization-data.json");
            default -> throw new IllegalArgumentException("Unexpected value: " + fileName);
        };
    }
}
