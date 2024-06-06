package com.bookstore.models;


import java.util.List;
import java.util.Objects;
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
    List<String> categories;
public Book(){}

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
    public Book categories(List<String> categories){
        this.categories = categories;
        return this;
    }
    public Book build(){
        return this;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle=" + subtitle +
                ", description='" + description + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", year=" + year +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(subtitle, book.subtitle) && Objects.equals(description, book.description) && Objects.equals(authors, book.authors) && Objects.equals(publisher, book.publisher) && Objects.equals(pages, book.pages) && Objects.equals(year, book.year) && Objects.equals(image, book.image) && Objects.equals(price, book.price) && Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, description, authors, publisher, pages, year, image, price, categories);
    }
}
