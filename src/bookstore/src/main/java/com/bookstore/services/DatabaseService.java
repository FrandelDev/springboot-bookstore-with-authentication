package com.bookstore.services;

import com.bookstore.mappers.BookMapper;
import com.bookstore.mappers.UserMapper;
import com.bookstore.models.Book;
import com.bookstore.models.UserEntity;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * This service class is used to interact with the database for operations related to books.
 * It implements the BookRepository interface and uses JdbcTemplate for database operations.
 */

@Service
@Qualifier("databaseService")
public class DatabaseService implements BookRepository, UserRepository {
    @Autowired
    private JdbcTemplate template;


    @Override
    public Book getBookById(String id) {
        String sql = "SELECT * FROM BOOKS WHERE ID = ?";
        return template.queryForObject(sql,new BookMapper(),id);
    }

    @Override
    public int insertNewBook(Book book, String reader) {
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
                CATEGORIES,
                READER
                ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)""";


       return template.update(sql,
                book.getId(),
                book.getTitle(),
                book.getSubtitle().orElse(""),
                book.getDescription(),
                book.getAuthors(),
                book.getPublisher(),
                book.getPages(),
                book.getYear(),
                book.getImage(),
                book.getPrice(),
                String.join(",",book.getCategories()),
               reader
        );
    }

    @Override
    public List<Book> getAllBooks(String reader) {
        String sql = "SELECT * FROM BOOKS  INNER JOIN  USERS ON BOOKS.READER = USERS.USERNAME WHERE READER = ?";
        return template.query(sql, new BookMapper(),reader);
    }

    @Override
    public boolean removeBook(String id) {
        String sql = "DELETE FROM BOOKS WHERE ID = ?";
       int rowsAffected = template.update(sql,id);
        return rowsAffected != 0;
    }



    // USERS
    @Override
    public UserEntity getUser(String username) {
        return template.queryForObject("SELECT * FROM USERS WHERE username = ?",new UserMapper(),username);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return null;
    }
}

