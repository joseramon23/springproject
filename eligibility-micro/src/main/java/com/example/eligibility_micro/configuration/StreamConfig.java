package com.example.eligibility_micro.configuration;

import com.example.eligibility_micro.common.LetterCreatedEvent;
import com.example.eligibility_micro.common.LetterEligibleEvent;
import com.example.eligibility_micro.common.PackageCreatedEvent;
import com.example.eligibility_micro.common.PackageEligibleEvent;
import com.example.eligibility_micro.processors.EligibilityLetterProcessor;
import com.example.eligibility_micro.processors.EligibilityPackageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class StreamConfig {
    @Bean
    public Function<Flux<PackageCreatedEvent>, Flux<PackageEligibleEvent>> packageCreatedBinding(final EligibilityPackageProcessor processor) {
        return processor::process;
    }

    @Bean
    public Function<Flux<LetterCreatedEvent>, Flux<LetterEligibleEvent>> letterCreatedBinding(final EligibilityLetterProcessor processor) {
        return processor::process;
    }
}
