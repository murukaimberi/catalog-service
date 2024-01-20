package com.afrikatek.catalogservice.web.rest;

import com.afrikatek.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetRequestWithIdThenBookReturned() {
        var bookIsbn = "1234567890";
        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
        Book excpectedBook = webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(book -> assertThat(book).isNotNull())
                .returnResult().getResponseBody();
    }

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = new Book("1234567893", "Title", "Author", 9.90);

        webTestClient
                .post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(book -> assertThat(book).isNotNull());
    }

    @Test
    void whenPutRequestThenBookUpdated() {
        var bookIsbn = "1234567891";
        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
        Book bookCreated = webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(actualBook -> assertThat(actualBook).isNotNull())
                .returnResult().getResponseBody();

        var bookToUpdate = new Book(bookCreated.isbn(), bookCreated.title(), bookCreated.author(), 7.95);

        webTestClient
                .put()
                .uri("/books/" + bookIsbn)
                .bodyValue(bookToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class).value(actualBook -> {
                    assertThat(actualBook).isNotNull();
                    assertThat(actualBook.price()).isEqualTo(bookToUpdate.price());
                });
    }

    @Test
    void whenDeleteRequestThenBookDeleted() {
        var bookIsbn = "1234567892";
        var bookToCreate = new Book(bookIsbn, "Title", "Author", 9.90);
        webTestClient
                .post()
                .uri("/books")
                .bodyValue(bookToCreate)
                .exchange()
                .expectStatus().isCreated();

        webTestClient
                .delete()
                .uri("/books/" + bookIsbn)
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .get()
                .uri("/books/" + bookIsbn)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).value(errorMessage ->
                        assertThat(errorMessage).isEqualTo("The book with ISBN " + bookIsbn + " was not found.")
                );
    }
}
