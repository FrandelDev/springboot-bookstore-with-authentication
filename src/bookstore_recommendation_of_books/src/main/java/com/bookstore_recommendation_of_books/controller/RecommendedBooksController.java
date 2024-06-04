package com.bookstore_recommendation_of_books.controller;

import com.bookstore_recommendation_of_books.model.Book;
import com.bookstore_recommendation_of_books.service.RecommendBooksService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@CrossOrigin //This tag help to allow fetching from unknown origins.
public class RecommendedBooksController {

    @GetMapping()
    public List<Book> getRecommendedBooks(){
        return RecommendBooksService.recommendedBooksAndApplyDiscounts();
    }
}
