package com.example.eligibility_micro.services.impl;

import com.example.eligibility_micro.common.PackageCreatedEvent;
import com.example.eligibility_micro.common.PackageEligibleEvent;
import com.example.eligibility_micro.services.PackageEligibleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class PackageEligibleServiceImpl implements PackageEligibleService {

    @Override
    public Mono<PackageEligibleEvent> eligibilityPackage(PackageCreatedEvent packageCreatedEvent) {
        return Mono.just(packageCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> PackageEligibleEvent.builder()
                        .id(givenCreated.getId())
                        .sender(givenCreated.getSender())
                        .receiver(givenCreated.getReceiver())
                        .origin(givenCreated.getOrigin())
                        .destination(givenCreated.getDestination())
                        .isEligible(true)
                        .build());
    }

    private Mono<PackageCreatedEvent> checkIsEligible(PackageCreatedEvent packageCreatedEvent) {
        return Mono.just(packageCreatedEvent)
                .filter(Objects::nonNull)
                .map(given -> packageCreatedEvent);
    }
}
