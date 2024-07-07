package com.example.api_package.services;

import com.example.api_package.commons.entities.Package;

import java.util.ArrayList;

public interface PackageService {
    ArrayList<Package> getPackages();

    Package getPackage(Long id);

    Package createPackage(Package packageRequest);

    Package updatePackage(Long id, Package updatePackage);

    String deletePackage(Long id);

    Package changePackageStatus(Long id);
}
