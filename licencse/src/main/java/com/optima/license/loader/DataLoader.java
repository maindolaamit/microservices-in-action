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

    public void run(String... args) throws IOException {
        log.info("Loading data...");
        List<License> licenseList = getTypedList("license-data.json");
        repository.saveAll(licenseList);
        log.info("Data loaded successfully, {} licenses added", licenseList.size());
    }

    private <T> List<T> getTypedList(String fileName) throws IOException {
        return mapper.readValue(getFilePath(fileName), new TypeReference<>() {
        });
    }

    private File getFilePath(String fileName) {
        return switch (fileName) {
            case "license-data.json" -> new File("src/main/resources/data/license-data.json");
            case "organization-data.json" -> new File("src/main/resources/data/organization-data.json");
            default -> throw new IllegalArgumentException("Unexpected value: " + fileName);
        };
    }
}
