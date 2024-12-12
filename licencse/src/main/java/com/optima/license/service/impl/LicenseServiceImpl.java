package com.optima.license.service.impl;

import com.optima.license.entity.License;
import com.optima.license.service.LicenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    @Override
    public License getLicense(String licenseId, String organizationId) {
        return new License(new Random().nextInt(1000), licenseId,
                "Software product", organizationId,
                "Ostock", "full");
//        return License.builder().id(new Random().nextInt(1000))
//                .licenseId(licenseId)
//                .organizationId(organizationId)
//                .description("Software product")
//                .productName("Ostock")
//                .licenseType("full")
//                .build();
    }


    @Override
    public String createLicense(License license, String organizationId) {
        String response = null;
        if (license != null) response = String.format("License for %s created", license.getProductName());
        return response;
    }

    @Override
    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format("This is the put and the object is: %s", license);
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
        return responseMessage;
    }
}
