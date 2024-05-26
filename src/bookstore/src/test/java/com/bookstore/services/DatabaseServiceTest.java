package com.bookstore.services;

import com.bookstore.mappers.BookMapper;
import com.bookstore.models.Book;
import com.bookstore.services.DatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DatabaseServiceTest {

    @InjectMocks
    DatabaseService databaseService;

    @Mock
    JdbcTemplate jdbcTemplate;

    Book exampleBook =Book.builder()
                .id("3030168778")
                .title("Programming for Computations - Python")
                .subtitle(Optional.of("A Gentle Introduction to Numerical Simulations with Python 3.6"))
            .description("This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...")
                .authors("Svein Linge, Hans Petter Langtangen")
                .publisher("Springer")
                .pages(350)
                .year(2020)
                .image("https://www.dbooks.org/img/books/3030168778s.jpg")
                .price(30.99)
                .categories(List.of(
            "Computational science and engineering",
                        "Computer science--mathematics",
                                    "Computer software",
                                    "Mathematical software",
                                    "Mathematics",
                                    "Numeric computing",
                                    "Numerical analysis"))
            .build();

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGetBookById() {
        Book book = new Book(
                exampleBook.getId(),
                exampleBook.getTitle(),
                exampleBook.getSubtitle(),
                exampleBook.getDescription(),
                exampleBook.getAuthors(),
                exampleBook.getPublisher(),
                exampleBook.getPages(),
                exampleBook.getYear(),
                exampleBook.getImage(),
                exampleBook.getPrice(),
                exampleBook.getCategories()
        );

        when(jdbcTemplate.queryForObject(anyString(), any(BookMapper.class), anyString())).thenReturn(book);

        Book result = databaseService.getBookById("3030168778");
        assertEquals(book, result);
    }

    @Test
    public void testInsertNewBook() {
        Book book = new Book(
                exampleBook.getId(),
                exampleBook.getTitle(),
                exampleBook.getSubtitle(),
                exampleBook.getDescription(),
                exampleBook.getAuthors(),
                exampleBook.getPublisher(),
                exampleBook.getPages(),
                exampleBook.getYear(),
                exampleBook.getImage(),
                exampleBook.getPrice(),
                exampleBook.getCategories()
        );

        databaseService.insertNewBook(book);
        verify(jdbcTemplate).update(anyString(),
                eq(book.getId()),
                eq(book.getTitle()),
                eq(book.getSubtitle().isPresent() ? book.getSubtitle().get():""),
                eq(book.getDescription()),
                eq(book.getAuthors()),
                eq(book.getPublisher()),
                eq(book.getPages()),
                eq(book.getYear()),
                eq(book.getImage()),
                eq(book.getPrice()),
                eq(String.join(",", book.getCategories())));
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(new Book(
                exampleBook.getId(),
                exampleBook.getTitle(),
                exampleBook.getSubtitle(),
                exampleBook.getDescription(),
                exampleBook.getAuthors(),
                exampleBook.getPublisher(),
                exampleBook.getPages(),
                exampleBook.getYear(),
                exampleBook.getImage(),
                exampleBook.getPrice(),
                exampleBook.getCategories()
        ));
        when(jdbcTemplate.query(anyString(), any(BookMapper.class))).thenReturn(books);

        List<Book> result = databaseService.getAllBooks();
        assertEquals(books, result);
    }

    @Test
    public void testRemoveBook() {
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);

        boolean result = databaseService.removeBook("3030168778");
        assertTrue(result);
    }
}
