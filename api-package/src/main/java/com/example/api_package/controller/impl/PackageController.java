package com.example.api_package.controller.impl;

import com.example.api_package.commons.entities.Package;
import com.example.api_package.controller.ApiPackage;
import com.example.api_package.services.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PackageController implements ApiPackage {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public ResponseEntity<ArrayList<Package>> getPackages() {
        return ResponseEntity.ok(this.packageService.getPackages());
    }

    @Override
    public ResponseEntity<Package> getPackageById(String id) {
        return ResponseEntity.ok(this.packageService.getPackage(Long.valueOf(id)));
    }

    @Override
    public ResponseEntity<Package> savePackage(@RequestBody Package packageRequest, @RequestHeader("userIdRequest") String userId) {
        packageRequest.setSender(Integer.valueOf(userId));
        return ResponseEntity.ok(this.packageService.createPackage(packageRequest));
    }

    @Override
    public ResponseEntity<Package> updatePackage(String id, Package packageRequest) {
        return ResponseEntity.ok(this.packageService.updatePackage(Long.valueOf(id), packageRequest));
    }

    @Override
    public ResponseEntity<Package> updatePackageStatus(String id) {
        Package packageUpdated = this.packageService.changePackageStatus(Long.valueOf(id));
        return ResponseEntity.ok(packageUpdated);
    }

    @Override
    public ResponseEntity<String> deletePackage(String id) {
        return ResponseEntity.ok(this.packageService.deletePackage(Long.valueOf(id)));
    }
}
