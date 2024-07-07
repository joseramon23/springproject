package com.example.eligibility_micro.services;

import com.example.eligibility_micro.common.PackageCreatedEvent;
import com.example.eligibility_micro.common.PackageEligibleEvent;
import reactor.core.publisher.Mono;

public interface PackageEligibleService {
    Mono<PackageEligibleEvent> eligibilityPackage(PackageCreatedEvent packageCreatedEvent);
}
