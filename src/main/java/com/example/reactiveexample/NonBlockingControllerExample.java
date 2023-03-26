package com.example.reactiveexample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/nonblocking")
public class NonBlockingControllerExample {

    @GetMapping("/slow")
    public Mono<ResponseEntity<String>> slowOperation() {
        // Simulate a slow operation (e.g., slow database query or external API call)
        return Mono.delay(Duration.ofSeconds(2))
                .map(i -> ResponseEntity.ok("Slow operation completed."))
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during sleep."));
    }

}