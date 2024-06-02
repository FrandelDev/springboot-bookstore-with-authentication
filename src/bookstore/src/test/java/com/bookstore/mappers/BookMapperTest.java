package com.bookstore.mappers;

import com.bookstore.Data;
import com.bookstore.models.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookMapperTest {
    @MockBean
    private BookMapper bookMapper;

    private Data data = new Data();


    @Test
    void mapRow() throws SQLException {

        ResultSet rs = Mockito.mock(ResultSet.class);

        when(rs.getString("id")).thenReturn("1");
        when(rs.getString("title")).thenReturn("Test Title");
        when(rs.getString("subtitle")).thenReturn("Test Subtitle");
        when(rs.getString("description")).thenReturn("Test Description");
        when(rs.getString("authors")).thenReturn("Test Author");
        when(rs.getString("publisher")).thenReturn("Test Publisher");
        when(rs.getInt("pages")).thenReturn(100);
        when(rs.getInt("published_year")).thenReturn(2020);
        when(rs.getString("image")).thenReturn("Test Image");
        when(rs.getDouble("price")).thenReturn(19.99);
        when(rs.getString("categories")).thenReturn("Test Category");


        BookMapper mapper = new BookMapper();
        Book book = mapper.mapRow(rs, 1);


        assert book != null;
        assertEquals("1", book.getId());
        assertEquals("Test Title", book.getTitle());
        assertEquals(Optional.of("Test Subtitle"), book.getSubtitle());
        assertEquals("Test Description", book.getDescription());
        assertEquals("Test Author", book.getAuthors());
        assertEquals("Test Publisher", book.getPublisher());
        assertEquals(100, book.getPages());
        assertEquals(2020, book.getYear());
        assertEquals("Test Image", book.getImage());
        assertEquals(19.99, book.getPrice());
        assertEquals(List.of("Test Category"), book.getCategories());
    }
}