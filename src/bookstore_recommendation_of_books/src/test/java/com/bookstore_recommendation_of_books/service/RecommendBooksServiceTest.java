package com.bookstore_recommendation_of_books.service;

import com.bookstore_recommendation_of_books.Data;
import com.bookstore_recommendation_of_books.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.bookstore_recommendation_of_books.service.RecommendBooksService.getBooksCategories;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RecommendBooksServiceTest {

    @Autowired
    RecommendBooksService recommendBooksService;

    @MockBean
    RestTemplate restTemplate;


    private Data data = new Data();

    @Test
    void getCategories(){
        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> toInsert = new HashMap<>();
        toInsert.put("categories",data.categories);
        expected.add(toInsert);

        when(restTemplate.getForObject(eq("http://bookstore:8383/api/bookstore"), eq(List.class))).thenReturn(expected);

        List<String> categories = getBooksCategories();

        assertEquals("Technology",categories.getFirst());
        verify(restTemplate).getForObject(eq("http://bookstore:8383/api/bookstore"), eq(List.class));
    }



}
