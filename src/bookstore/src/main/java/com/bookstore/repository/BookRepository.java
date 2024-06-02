package com.bookstore.repository;

import com.bookstore.models.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    Book getBookById(String id);
    int insertNewBook(Book book);
    List<Book> getAllBooks();
    boolean removeBook(String id);
}
