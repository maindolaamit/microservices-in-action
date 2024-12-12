package com.optima.license.controller;

import com.optima.license.entity.License;
import com.optima.license.service.LicenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get License", description = "API to get a license")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(organizationId, licenseId);
        return ResponseEntity.ok(license);
    }


    @PostMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update License", description = "API to update a license")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request,
                organizationId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create License", description = "API to create a license")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request) {
        return ResponseEntity.ok(licenseService.createLicense(request,
                organizationId));
    }

    @DeleteMapping(value="/{licenseId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete License", description = "API to delete a license")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId,
                organizationId));
    }
}
