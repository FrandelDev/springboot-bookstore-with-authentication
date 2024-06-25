package com.bookstore.repository;

import com.bookstore.models.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    Book getBookById(String id);
    int insertNewBook(Book book,String reader);
    List<Book> getAllBooks(String reader);
    boolean removeBook(String id);
}
