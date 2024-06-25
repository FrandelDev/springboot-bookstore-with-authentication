package com.bookstore.services;

import com.bookstore.Data;
import com.bookstore.mappers.BookMapper;
import com.bookstore.models.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class is used to test the DatabaseService class.
 * It uses Mockito to mock the JdbcTemplate and the DatabaseService.
 */
@ExtendWith(MockitoExtension.class)
class DatabaseServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;


    @InjectMocks
    private DatabaseService databaseService;

    private final Data data = new Data();

    @Test
    void getBookById() {
        String id = "3030168779";
        String query = "SELECT * FROM BOOKS WHERE ID = ?";
        when(jdbcTemplate.queryForObject(eq(query), any(BookMapper.class), eq(id))).thenReturn(data.allBooks.getFirst());

        Book actualBook = databaseService.getBookById(id);

        assertEquals(data.allBooks.getFirst(), actualBook);

        verify(jdbcTemplate).queryForObject(eq(query), any(BookMapper.class), eq(id));
    }

    @Test
    void insertNewBook() {
        String query = """
                INSERT INTO BOOKS(
                ID,
                TITLE,
                SUBTITLE,
                DESCRIPTION,
                AUTHORS,
                PUBLISHER,
                PAGES,
                PUBLISHED_YEAR,
                IMAGE,
                PRICE,
                CATEGORIES) VALUES (?,?,?,?,?,?,?,?,?,?,?)""";
        Book book = data.allBooks.getFirst();

        when(jdbcTemplate.update(eq(query),
                eq(book.getId()),
                eq(book.getTitle()),
                eq(book.getSubtitle().isPresent() ? book.getSubtitle().get() : ""),
                eq(book.getDescription()),
                eq(book.getAuthors()),
                eq(book.getPublisher()),
                eq(book.getPages()),
                eq(book.getYear()),
                eq(book.getImage()),
                eq(book.getPrice()),
                eq(String.join(",", book.getCategories()))
        )).thenReturn(1);

        int rowsAffected = databaseService.insertNewBook(book,"admin");
        assertTrue(rowsAffected > 0);

        verify(jdbcTemplate).update(eq(query),
                eq(book.getId()),
                eq(book.getTitle()),
                eq(book.getSubtitle().isPresent() ? book.getSubtitle().get() : ""),
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
    void getAllBooks() {

        String query = "SELECT * FROM BOOKS";
        when(jdbcTemplate.query(eq(query), any(BookMapper.class))).thenReturn(data.allBooks);

        List<Book> allBooks = databaseService.getAllBooks("admin");

        assertEquals(data.allBooks, allBooks);

        verify(jdbcTemplate).query(eq(query), any(BookMapper.class));
    }

    @Test
    void removeBook() {
        String query = "DELETE FROM BOOKS WHERE ID = ?";
        when(jdbcTemplate.update(eq(query),anyString())).thenReturn(1);

        boolean isSuccess = databaseService.removeBook("3030168779");
        assertTrue(isSuccess);
    }
}