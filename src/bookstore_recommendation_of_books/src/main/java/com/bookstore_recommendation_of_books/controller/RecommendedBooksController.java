package com.bookstore_recommendation_of_books.controller;

import com.bookstore_recommendation_of_books.model.Book;
import com.bookstore_recommendation_of_books.service.RecommendBooksService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:8080","http://localhost:8384","http://127.0.0.1:5500"})
public class RecommendedBooksController {

    @GetMapping()
    public ResponseEntity<List<Book>> getRecommendedBooks(@RequestHeader(value = "Authorization", required = false) String jwtAuth) throws AuthenticationException {
        if(jwtAuth == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).build();
        return ResponseEntity.ok().body(RecommendBooksService.recommendedBooksAndApplyDiscounts(jwtAuth));
    }

}
