package com.bookstore_recommendation_of_books.model;

import java.util.List;
import java.util.Optional;

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
    Double priceWithDiscount;
    List<String> categories;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Optional<String> getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public List<String> getCategories() {
        return categories;
    }


    public Book id(String id){
        this.id = id;
        return this;
    }
    public Book title(String title){
        this.title = title;
        return this;
    }
    public Book subtitle(Optional<String> subtitle){
        this.subtitle = subtitle;
        return this;
    }
    public Book description(String description){
        this.description = description;
        return this;
    }
    public Book authors(String authors){
        this.authors = authors;
        return this;
    }
    public Book publisher(String publisher){
        this.publisher = publisher;
        return this;
    }
    public Book pages(Integer pages){
        this.pages = pages;
        return this;
    }
    public Book year(Integer year){
        this.year = year;
        return this;
    }
    public Book image(String image){
        this.image = image;
        return this;
    }
    public Book price(Double price){
        this.price = price;
        return this;
    }
    public Book priceWithDiscount(Double priceWithDiscount){
        this.priceWithDiscount = priceWithDiscount;
        return this;
    }
    public Book categories(List<String> categories){
        this.categories = categories;
        return this;
    }
    public Book build(){
        return this;
    }
}
