package com.bookstore_recommendation_of_books.service;

import com.bookstore_recommendation_of_books.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class RecommendBooksService {
    public static void getBooksCategories(){
        RestTemplate template = new RestTemplate();
        List<String> allCategories = new ArrayList<>();
        List<Map<String, Book>> result = template.getForObject("http://localhost:8383/api/bookstore", List.class);


        System.out.println(result.toArray().length);

    }
}
