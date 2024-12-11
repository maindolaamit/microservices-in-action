package com.optimagrowth.license.service;

import com.optimagrowth.license.models.entity.License;

public interface LicenseService {
    License getLicense(String licenseId, String organizationId);

    String createLicense(License license, String organizationId);

    String updateLicense(License license, String organizationId);

    String deleteLicense(String licenseId, String organizationId);
}
