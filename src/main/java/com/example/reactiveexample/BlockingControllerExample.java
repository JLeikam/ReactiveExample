package com.example.reactiveexample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blocking")
public class BlockingControllerExample {

    @GetMapping("/slow")
    public ResponseEntity<String> slowOperation() {
        try {
            // Simulate a slow operation (e.g., slow database query or external API call)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during sleep.");
        }
        return ResponseEntity.ok("Slow operation completed.");
    }

}
