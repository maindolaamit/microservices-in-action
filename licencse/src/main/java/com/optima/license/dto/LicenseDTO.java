package com.optima.license.dto;

import lombok.Builder;

@Builder
public record LicenseDTO(int id,
                         String licenseId,
                         String description,
                         String organizationId,
                         String productName,
                         String licenseType) {
}
