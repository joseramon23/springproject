package com.example.eligibility_micro.services;

import com.example.eligibility_micro.common.LetterCreatedEvent;
import com.example.eligibility_micro.common.LetterEligibleEvent;
import reactor.core.publisher.Mono;

public interface LetterEligibleService {
    Mono<LetterEligibleEvent> eligibilityLetter(LetterCreatedEvent letterCreatedEvent);
}
