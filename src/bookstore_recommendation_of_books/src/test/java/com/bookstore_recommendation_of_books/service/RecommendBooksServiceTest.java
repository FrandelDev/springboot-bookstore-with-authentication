package com.bookstore_recommendation_of_books.service;

import com.bookstore_recommendation_of_books.model.Book;
import com.bookstore_recommendation_of_books.service.RecommendBooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RecommendBooksServiceTest {

    @InjectMocks
    RecommendBooksService recommendBooksService;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


}
