package com.example.reactiveexample;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoExampleTests {
    @Test
    public void testFlux() {
        Flux<String> stringFlux = Flux.just("A", "B", "C");

        StepVerifier.create(stringFlux)
                .expectNext("A")
                .expectNext("B")
                .expectNext("C")
                .verifyComplete();
    }

    @Test
    public void testMono() {
        Mono<String> stringMono = Mono.just("A");

        StepVerifier.create(stringMono)
                .expectNext("A")
                .verifyComplete();
    }

    @Test
    public void testFluxWithError() {
        Flux<String> stringFlux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("An error occurred")));

        StepVerifier.create(stringFlux)
                .expectNext("A")
                .expectNext("B")
                .expectNext("C")
                .expectErrorMessage("An error occurred")
                .verify();
    }

    @Test
    public void testMonoWithError() {
        Mono<String> stringMono = Mono.error(new RuntimeException("An error occurred"));

        StepVerifier.create(stringMono)
                .expectError(RuntimeException.class)
                .verify();
    }
}
