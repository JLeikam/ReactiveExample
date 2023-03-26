package com.example.reactiveexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookController.class)
public class BookControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository bookRepository;

    private Book springInAction;
    private Book designPatterns;

    @BeforeEach
    public void setUp() {
        springInAction = new Book("1", "Spring in Action", "Craig Walls", "Non-Fiction");
        designPatterns = new Book("2", "Design Patterns", "Erich Gamma", "Non-Fiction");

        when(bookRepository.findAll()).thenReturn(Flux.just(springInAction, designPatterns));
        when(bookRepository.findById("1")).thenReturn(Mono.just(springInAction));
        when(bookRepository.findByAuthor("AuthorOne")).thenReturn(Flux.just(springInAction));
        when(bookRepository.save(any(Book.class))).thenReturn(Mono.just(springInAction));
        when(bookRepository.deleteById("1")).thenReturn(Mono.empty());
    }

    @Test
    public void testGetAllBooks() {
        webTestClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .hasSize(2)
                .contains(springInAction, designPatterns);
    }

    @Test
    public void testGetBookById() {
        webTestClient.get()
                .uri("/api/books/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .isEqualTo(springInAction);
    }

    @Test
    public void testGetBooksByAuthor() {
        webTestClient.get()
                .uri("/api/books/author/AuthorOne")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .consumeWith(System.out::println)
                .hasSize(1)
                .contains(springInAction);
    }

    @Test
    public void testCreateBook() {
        webTestClient.post()
                .uri("/api/books")
                .bodyValue(springInAction)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .isEqualTo(springInAction);
    }

    @Test
    public void testUpdateBook() {
        Book updatedBook = new Book("1", "Updated Book One", "Author One", "Fiction");
        when(bookRepository.save(updatedBook)).thenReturn(Mono.just(updatedBook));

        webTestClient.put()
                .uri("/api/books/1")
                .bodyValue(updatedBook)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .isEqualTo(updatedBook);
    }

    @Test
    public void testDeleteBook() {
        webTestClient.delete()
                .uri("/api/books/1")
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);

        verify(bookRepository, times(1)).deleteById("1");
    }
}