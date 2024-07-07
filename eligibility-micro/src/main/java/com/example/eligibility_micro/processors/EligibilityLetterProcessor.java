package com.example.eligibility_micro.processors;

import com.example.eligibility_micro.common.LetterCreatedEvent;
import com.example.eligibility_micro.common.LetterEligibleEvent;
import com.example.eligibility_micro.services.LetterEligibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityLetterProcessor {
    private final LetterEligibleService letterEligibleService;

    public EligibilityLetterProcessor(LetterEligibleService letterEligibleService) {
        this.letterEligibleService = letterEligibleService;
    }

    public Flux<LetterEligibleEvent> process(Flux<LetterCreatedEvent> letterCreatedEventFlux) {
        return letterCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}", given))
                .flatMap(letterEligibleService::eligibilityLetter)
                .onErrorContinue(this::handleError);
    }

    private void handleError(Throwable throwable, Object o) {
        log.error("Error processing event: {}", o, throwable);
    }
}
