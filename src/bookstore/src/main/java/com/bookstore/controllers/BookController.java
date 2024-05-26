package com.bookstore.controllers;

import com.bookstore.models.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.services.AddCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    private @Qualifier("databaseService") BookRepository bookRepository;

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable String id){
       return bookRepository.getBookById(id);
    }

    @PostMapping("/book")
    public void setBook(@RequestBody Book bodyBook){
        StringBuilder purifierId = new StringBuilder(bodyBook.getId());
        if(purifierId.charAt(purifierId.length()-1) == 'X'){
            purifierId.deleteCharAt(purifierId.length()-1);
        }

        List<String> categories = AddCategoriesService.addCategories(bodyBook.getTitle());

        double price = new Random().nextDouble(15.00,50.99);

        Book book = Book.builder()
                .id(String.valueOf(purifierId))
                .title(bodyBook.getTitle())
                .subtitle(bodyBook.getSubtitle())
                .description(bodyBook.getDescription())
                .authors(bodyBook.getAuthors())
                .publisher(bodyBook.getPublisher())
                .pages(bodyBook.getPages())
                .year(bodyBook.getYear())
                .image(bodyBook.getImage())
                .price(Math.round(price*100.0)/100.0)
                .categories(categories)
                .build();
        bookRepository.insertNewBook(book);
    }

    @DeleteMapping("/book/{id}")
    public boolean deleteBook(@PathVariable String id){
        return bookRepository.removeBook(id);
    }

    @GetMapping()
    public List<Book> getAllBuyiedBooks(){
        return bookRepository.getAllBooks();
    }

    @GetMapping("/get-categories")
    @ResponseBody
    public List<String> getCategories(@RequestParam String bookTitle){
        return AddCategoriesService.addCategories(bookTitle);
    }
}
