package com.example.api_package.controller;

import com.example.api_package.commons.constants.ApiPathVariables;
import com.example.api_package.commons.entities.Package;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping(ApiPathVariables.V1_ROUTE + ApiPathVariables.PACKAGE_ROUTE)
public interface ApiPackage {
    @GetMapping
    ResponseEntity<ArrayList<Package>> getPackages();

    @GetMapping("/{id}")
    ResponseEntity<Package> getPackageById(@PathVariable String id);

    @PostMapping
    ResponseEntity<Package> savePackage(@RequestBody Package packageRequest, @RequestHeader("userIdRequest") String userId);

    @PutMapping("/{id}")
    ResponseEntity<Package> updatePackage(@PathVariable String id, Package packageRequest);

    @PutMapping("/{id}/status")
    ResponseEntity<Package> updatePackageStatus(@PathVariable String id);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePackage(@PathVariable String id);
}
