package com.afrikatek.catalogservice.persistence;

import com.afrikatek.catalogservice.domain.Book;
import com.afrikatek.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryBookRepository {
    private static final Map<String, Book> books = new ConcurrentHashMap<>();
    public Iterable<Book> findAll() {
        return books.values();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

    public boolean existsByIsbn(String isbn) {
        return books.containsKey(isbn);
    }

    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }
}
