package com.bookstore.services;

import com.bookstore.mappers.BookMapper;
import com.bookstore.models.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("databaseService")
public class DatabaseService implements BookRepository {
    @Autowired
    private JdbcTemplate template;


    @Override
    public Book getBookById(String id) {
        String sql = "SELECT * FROM BOOKS WHERE ID = ?";
        return template.queryForObject(sql,new BookMapper(),id);
    }

    @Override
    public int insertNewBook(Book book) {
        String sql = """
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


       return template.update(sql,
                book.getId(),
                book.getTitle(),
                book.getSubtitle().isPresent() ? book.getSubtitle().get():"",
                book.getDescription(),
                book.getAuthors(),
                book.getPublisher(),
                book.getPages(),
                book.getYear(),
                book.getImage(),
                book.getPrice(),
                String.join(",",book.getCategories())
        );
    }

    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM BOOKS";
        return template.query(sql, new BookMapper());
    }

    @Override
    public boolean removeBook(String id) {
        String sql = "DELETE FROM BOOKS WHERE ID = ?";
       int rowsAffected = template.update(sql,id);
        return rowsAffected != 0;
    }
}

