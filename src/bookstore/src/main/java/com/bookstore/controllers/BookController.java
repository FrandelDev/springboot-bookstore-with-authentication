package com.bookstore.controllers;

import com.bookstore.models.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.services.AddCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private @Qualifier("databaseService") BookRepository bookRepository;

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable String id){
       return bookRepository.getBookById(id);
    }


    /**
     * This method is used to create a new book and save it in the repository.
     * It takes a Book object as input and returns the created Book object.
     *
     * @PostMapping("/book") This annotation maps HTTP POST requests onto this method.
     * @ResponseStatus(HttpStatus.CREATED) This annotation sets the status code to 201 Created.
     *
     * @param bodyBook This is the book object received in the request body.
     * @return This returns the created book object.
     */
    @PostMapping("/book/{reader}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book setBook(@RequestBody Book bodyBook, @PathVariable String reader){
        StringBuilder purifierId = new StringBuilder(bodyBook.getId());
        if(purifierId.charAt(purifierId.length()-1) == 'X'){
            purifierId.deleteCharAt(purifierId.length()-1);
        }

        List<String> categories = bodyBook.getCategories() == null || bodyBook.getCategories().isEmpty()  ? AddCategoriesService.addCategories(bodyBook.getTitle()) :bodyBook.getCategories();

        double price = bodyBook.getPrice() == null ? new SecureRandom().nextDouble(15.00,50.99) :  bodyBook.getPrice();

        Book book = new Book()
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
        bookRepository.insertNewBook(book,reader);
        return book;
    }

    @DeleteMapping("/book/{id}")
    public boolean deleteBook(@PathVariable String id){
        return bookRepository.removeBook(id);
    }

    @GetMapping("/{reader}")
    public List<Book> getAllBuyiedBooks(@PathVariable String reader){
        return bookRepository.getAllBooks(reader);
    }

    @GetMapping("/get-categories")
    @ResponseBody
    public List<String> getCategories(@RequestParam String bookTitle){
        return AddCategoriesService.addCategories(bookTitle);
    }
}
