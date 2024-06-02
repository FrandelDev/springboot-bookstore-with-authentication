package com.bookstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;
import java.util.Optional;
@Data
@Builder
@AllArgsConstructor
public class Book {
    String id;
    String title;
    Optional<String> subtitle;
    String description;
    String authors;
    String publisher;
    Integer pages;
    Integer year;
    String image;
    Double price;
    List<String> categories;
public Book(){}
//    public Book(String id, String title, Optional<String> subtitle, String description, String authors, String publisher, Integer pages, Integer year, String image, Double price, List<String> categories) {
//        this.id = id;
//        this.title = title;
//        this.subtitle = subtitle;
//        this.description = description;
//        this.authors = authors;
//        this.publisher = publisher;
//        this.pages = pages;
//        this.year = year;
//        this.image = image;
//        this.price = price;
//        this.categories = categories;
//    }
}
