package com.example.eligibility_micro.processors;

import com.example.eligibility_micro.common.PackageCreatedEvent;
import com.example.eligibility_micro.common.PackageEligibleEvent;
import com.example.eligibility_micro.services.PackageEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityPackageProcessor {
    private final PackageEligibleService packageEligibleService;

    public EligibilityPackageProcessor(PackageEligibleService packageEligibleService) {
        this.packageEligibleService = packageEligibleService;
    }

    public Flux<PackageEligibleEvent> process(Flux<PackageCreatedEvent> packageCreatedEventFlux) {
        return packageCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(packageEligibleService::eligibilityPackage)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object o) {
        log.error("Error processing event: {}", o, throwable);
    }
}
