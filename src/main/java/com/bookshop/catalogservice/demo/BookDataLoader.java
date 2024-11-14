package com.bookshop.catalogservice.demo;

import com.bookshop.catalogservice.domain.Book;
import com.bookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Northern Lights",
                "Lyra Silverstar", 9.90, "Penguin Books");
        var book2 = Book.of("1234567892", "Polar Journey",
                "Iorek Polarson", 12.90, "Penguin Books");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
