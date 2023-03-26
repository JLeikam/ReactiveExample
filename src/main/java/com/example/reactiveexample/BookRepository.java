package com.example.reactiveexample;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {
    Flux<Book> findByAuthor(String author);
}