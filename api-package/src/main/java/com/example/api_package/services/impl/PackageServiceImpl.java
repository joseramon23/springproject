package com.example.api_package.services.impl;

import com.example.api_package.commons.constants.TopicConstants;
import com.example.api_package.commons.entities.Package;
import com.example.api_package.commons.enums.PackageStatusEnum;
import com.example.api_package.commons.exceptions.ParcelException;
import com.example.api_package.repositories.PackageRepository;
import com.example.api_package.services.PackageService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {
    public final PackageRepository packageRepository;
    public final StreamBridge streamBridge;

    public PackageServiceImpl(PackageRepository parcelRepository, StreamBridge streamBridge) {
        this.packageRepository = parcelRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public ArrayList<Package> getPackages() {
        return (ArrayList<Package>) this.packageRepository.findAll();
    }

    @Override
    public Package getPackage(Long id) {
        return this.packageRepository.findById(id)
                .orElseThrow(() -> new ParcelException(HttpStatus.NOT_FOUND, "Error finding package"));
    }

    @Override
    public Package createPackage(Package packageRequest) {
        return Optional.of(packageRequest)
                .map(this::mapToPackage)
                .map(packageRepository::save)
                .map(this::sendPackageEvent)
                .orElseThrow(() -> new ParcelException(HttpStatus.BAD_REQUEST, "Error creating package"));
    }

    private Package sendPackageEvent(Package packageRequest) {
        Optional.of(packageRequest)
                .map(givenPackage -> this.streamBridge.send(TopicConstants.PACKAGE_CREATED_TOPIC, packageRequest))
                .map(bool -> packageRequest);

        return packageRequest;
    }

    private Package mapToPackage(Package packageRequest) {
        return Package.builder()
                .sender(packageRequest.getSender())
                .receiver(packageRequest.getReceiver())
                .origin(packageRequest.getOrigin())
                .destination(packageRequest.getDestination())
                .sender(packageRequest.getSender())
                .status(PackageStatusEnum.SENT)
                .build();
    }

    @Override
    public Package updatePackage(Long id, Package updatePackage) {
        Package existsPackage = this.packageRepository.findById(id)
                .orElseThrow(() -> new ParcelException(HttpStatus.NOT_FOUND, "Error finding package"));

        existsPackage.setDestination(updatePackage.getDestination());
        existsPackage.setDestination(updatePackage.getDestination());
        existsPackage.setStatus(updatePackage.getStatus());

        return this.packageRepository.save(existsPackage);
    }

    @Override
    public String deletePackage(Long id) {
        Package existsPackage = this.packageRepository.findById(id)
                .orElseThrow(() -> new ParcelException(HttpStatus.NOT_FOUND, "Error finding package"));

        this.packageRepository.deleteById(id);
        return "Package ID: " + id + " Has been deleted";
    }

    @Override
    public Package changePackageStatus(Long id) {
        Package packageToUpdate = this.packageRepository.findById(id)
                .orElseThrow(() -> new ParcelException(HttpStatus.NOT_FOUND, "Error finding package"));

        switch (packageToUpdate.getStatus()) {
            case SENT:
                packageToUpdate.setStatus(PackageStatusEnum.IN_TRANSIT);
                break;
            case IN_TRANSIT:
                packageToUpdate.setStatus(PackageStatusEnum.DELIVERED);
                break;
            default:
                throw new ParcelException(HttpStatus.BAD_REQUEST, "Error updating package status");
        }

        return this.packageRepository.save(packageToUpdate);
    }
}
