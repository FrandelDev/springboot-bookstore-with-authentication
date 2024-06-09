package com.bookstore.controllers;

import com.bookstore.models.Book;
import com.bookstore.services.DbBooksApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbBooksApiController {

    @GetMapping("/recent")
    public List<Book> getRecentBooks(){
        return DbBooksApiService.getRecentBooks();
    }
    @GetMapping("/search")
    public List<Book> searchBooks (@RequestParam String criteria){
        return DbBooksApiService.getBooksBySearch(criteria);
    }
}
