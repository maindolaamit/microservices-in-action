package com.optima.license.repository;

import com.optima.license.entity.License;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LicenseRepository {
    private final List<License> data;

    public List<License> getLicenses() {
        return data;
    }

    public License getLicense(String licenseId) {
        return data.stream()
                .filter(license -> license.getLicenseId().equals(licenseId))
                .findFirst()
                .orElse(null);
    }

    public void saveAll(List<License> licenses) {
        data.addAll(licenses);
    }

    public void save(License license) {
        data.add(license);
    }

    public License updateLicense(License license) {
        License existingLicense = data.stream()
                .filter(l -> l.getLicenseId().equals(license.getLicenseId()))
                .findFirst()
                .orElse(null);
        if (existingLicense != null) {
            data.remove(existingLicense);
            data.add(license);
        }
        return license;
    }

    public void removeLicense(String licenseId) {
        License existingLicense = data.stream()
                .filter(l -> l.getLicenseId().equals(licenseId))
                .findFirst()
                .orElse(null);
        if (existingLicense != null) {
            data.remove(existingLicense);
        }
    }
}
