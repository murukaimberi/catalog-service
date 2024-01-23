package com.afrikatek.catalogservice.demo;

import com.afrikatek.catalogservice.domain.Book;
import com.afrikatek.catalogservice.repository.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book = new Book("123456789", "Northen Lights", "Lyra Sliverstar", 9.90);
        var book1 = new Book("987625262", "Northen Lights One", "Lyra Johns", 9.90);
        bookRepository.save(book);
        bookRepository.save(book1);
    }
}
