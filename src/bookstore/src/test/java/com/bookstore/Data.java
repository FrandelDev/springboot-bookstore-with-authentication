package com.bookstore;

import com.bookstore.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Data {
    public List<Book> allBooks = new ArrayList<>();
    public  List<String> categories = new ArrayList<>();

    public Data(){
        allBooks.add(
                new Book(
                        "3030168779",
                        "Programming for Computations - Python",
                        Optional.of("A Gentle Introduction to Numerical Simulations with Python 3.6"),
                        "This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...",
                        "Svein Linge, Hans Petter Langtangen",
                        "Springer",
                        350,
                        2020,
                        "https://www.dbooks.org/img/books/3030168778s.jpg",
                        30.99,
                        List.of(
                                "Computational science and engineering",
                                "Computer science--mathematics",
                                "Computer software",
                                "Mathematical software",
                                "Mathematics",
                                "Numeric computing",
                                "Numerical analysis")
                )
        );
        allBooks.add(
                new Book(
                        "303016877X",
                        "Programming for Computations - Python",
                        Optional.of("A Gentle Introduction to Numerical Simulations with Python 3.6"),
                        "This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...",
                        "Svein Linge, Hans Petter Langtangen",
                        "Springer",
                        350,
                        2020,
                        "https://www.dbooks.org/img/books/3030168778s.jpg",
                        null,
                        List.of()
        ));

        categories.addAll(List.of("Technology", "Python", "Computer Programming"));
    }

}
