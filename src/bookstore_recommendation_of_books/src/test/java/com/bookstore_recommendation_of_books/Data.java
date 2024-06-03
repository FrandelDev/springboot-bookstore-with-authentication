package com.bookstore_recommendation_of_books;
import com.bookstore_recommendation_of_books.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Data {
    public List<Book> allbooks = new ArrayList<>();
    public List<String> categories = new ArrayList<>();

    public Data() {
        Book book = new Book();

        book
                .id("3030168779")
                .title("Programming for Computations - Python")
                .subtitle(Optional.of("A Gentle Introduction to Numerical Simulations with Python 3.6"))
                .description("This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...")
                .authors("Svein Linge, Hans Petter Langtangen")
                .publisher("Springer")
                .pages(350)
                .year(2020)
                .image("https://www.dbooks.org/img/books/3030168778s.jpg")
                .price(30.99)
                .priceWithDiscount(23.99)
                .categories(List.of(
                        "Computational science and engineering",
                        "Computer science--mathematics",
                        "Computer software",
                        "Mathematical software",
                        "Mathematics",
                        "Numeric computing",
                        "Numerical analysis"))
                .build();
            allbooks.add(book);
        categories.addAll(List.of("Technology", "Python", "Computer Programming","code","Maths","Science","Logic","Engineering","py"));
    }
}
