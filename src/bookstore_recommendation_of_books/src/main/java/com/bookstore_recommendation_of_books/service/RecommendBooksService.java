package com.bookstore_recommendation_of_books.service;

import com.bookstore_recommendation_of_books.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class RecommendBooksService {
    public static List<String> getBooksCategories(){
        RestTemplate template = new RestTemplate();
        List<String> allCategories = new ArrayList<>();
        List<Map<String, Object>> result = template.getForObject("http://localhost:8383/api/bookstore", List.class);

        result.forEach(book ->{
          allCategories.addAll((List<String>)book.get("categories"));
        });

        return allCategories;

    }
}
