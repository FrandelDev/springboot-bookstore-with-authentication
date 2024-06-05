package com.bookstore.services;

import com.bookstore.Data;
import com.bookstore.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * This class is used to test the DbBooksApiService class.
 * It uses Mockito to mock the RestTemplate and the DbBooksApiService.
 */
@SpringBootTest
class DbBooksApiServiceTest {

    @Autowired
    DbBooksApiService service;

    @MockBean
    RestTemplate template;

    private Data data = new Data();

    @BeforeEach
    void setUp(){
        when(template.getForObject(anyString(),eq(Map.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                String url = (String) args[0];
                if(url.startsWith("https://www.dbooks.org/api/book/")){
                    List<Map<String,Object>> mockedBook = (List<Map<String, Object>>) data.bookMap.get("books");
                    return mockedBook.getFirst();
                }
                else {
                    return data.bookMap;
                }
            }
        });

    }

    @Test
    void getIdOfBooks() {

        List<String> ids = DbBooksApiService.getIdOfBooks("any",1);

        assertEquals("3030168779",ids.getFirst());

    verify(template).getForObject(anyString(),eq(Map.class));
    }

    @Test
    @Timeout(60)
    void getBooksBySearch() {
        List<Book> books= DbBooksApiService.getBooksBySearch("any");
        assertEquals("Programming for Computations - Python",books.getFirst().getTitle());
        verify(template,times(21)).getForObject(anyString(),eq(Map.class));
    }

    @Test
    @Timeout(60)
    void getRecentBooks() {
        List<Book> books= DbBooksApiService.getRecentBooks();
        assertEquals("Programming for Computations - Python",books.getFirst().getTitle());
        verify(template,times(21)).getForObject(anyString(),eq(Map.class));
    }
}