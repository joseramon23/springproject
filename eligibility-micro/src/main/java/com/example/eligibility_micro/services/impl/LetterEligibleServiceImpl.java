package com.example.eligibility_micro.services.impl;

import com.example.eligibility_micro.common.LetterCreatedEvent;
import com.example.eligibility_micro.common.LetterEligibleEvent;
import com.example.eligibility_micro.services.LetterEligibleService;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class LetterEligibleServiceImpl implements LetterEligibleService {
    @Override
    public Mono<LetterEligibleEvent> eligibilityLetter(LetterCreatedEvent letterCreatedEvent) {
        return Mono.just(letterCreatedEvent)
                .flatMap(this::checkIsEligible)
                .map(givenCreated -> LetterEligibleEvent.builder()
                        .id(givenCreated.getId())
                        .sender(givenCreated.getSender())
                        .receiver(givenCreated.getReceiver())
                        .content(givenCreated.getContent())
                        .destination(givenCreated.getDestination())
                        .date(givenCreated.getDate())
                        .isEligible(true)
                        .build());
    }

    private Mono<LetterCreatedEvent> checkIsEligible(LetterCreatedEvent letterCreatedEvent) {
        return Mono.just(letterCreatedEvent)
                .filter(Objects::nonNull)
                .map(given -> letterCreatedEvent);
    }
}
