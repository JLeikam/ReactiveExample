package com.example.reactiveexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> createNewBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @GetMapping
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/author/{author}")
    public Flux<Book> getBookByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    @PutMapping("/{id}")
    public Mono<Book> updateBookById(@PathVariable String id, @RequestBody Book book) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> updateAndSave(existingBook, book));
    }

    private Mono<Book> updateAndSave(Book existingBook, Book newBook) {
        updateBookDetails(existingBook, newBook);
        return bookRepository.save(existingBook);
    }

    private void updateBookDetails(Book existingBook, Book newBook) {
        existingBook.setTitle(newBook.getTitle());
        existingBook.setAuthor(newBook.getAuthor());
        existingBook.setGenre(newBook.getGenre());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBookById(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }
}
