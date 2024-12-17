package com.optima.license.service.impl;

import com.optima.license.entity.License;
import com.optima.license.repository.LicenseRepository;
import com.optima.license.service.LicenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;
    private final MessageSource messages;


    @Override
    public License getLicense(String licenseId, String organizationId) {
        return licenseRepository
                .findByOrganizationIdAndLicenseId(organizationId, licenseId)
                .orElse(
                        License.builder()
                                .licenseId(UUID.randomUUID().toString())
                                .organizationId(organizationId)
                                .description("Software product")
                                .productName("Ostock")
                                .licenseType("full")
                                .comment("")
                                .build()
                );
    }


    @Override
    public String createLicense(License license, String organizationId, Locale locale) {
        String response = null;
        if (license != null) {
            license.setLicenseId(UUID.randomUUID().toString());
            licenseRepository.save(license);
            response = String.format(messages.getMessage(
                            "license.create.message", null, locale),
                    license);
        }
        return response;
    }

    @Override
    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            val message = String.format(messages.getMessage(
                            "license.search.error.message", null, locale)
                    , license.getLicenseId());

            val result = licenseRepository
                    .findByOrganizationIdAndLicenseId(organizationId, license.getLicenseId())
                    .orElseThrow(() -> new IllegalArgumentException(message));

            result.setOrganizationId(organizationId);
            responseMessage = String.format(Objects.requireNonNull(messages.getMessage(
                            "license.update.message", null,
                            "License updated", null))
                    , license.getLicenseId());
            licenseRepository.save(result);
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        if (licenseId != null) {
            val result = licenseRepository
                    .findByLicenseId(licenseId)
                    .orElseThrow(() -> new IllegalArgumentException(messages
                            .getMessage("license.delete.notfound.message", new String[]{licenseId}, locale))
                    );
            licenseRepository.delete(result);
        }
        String responseMessage = null;
        responseMessage = messages.getMessage("license.delete.success.message", new String[]{licenseId}, locale);
//        responseMessage = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
        return responseMessage;
    }
}
