package com.example.api_package.repositories;

import com.example.api_package.commons.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
