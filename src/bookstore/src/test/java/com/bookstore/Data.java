package com.bookstore;

import com.bookstore.models.Book;

import java.util.*;

public class Data {
    public List<Book> allBooks = new ArrayList<>();
    public  List<String> categories = new ArrayList<>();
    public Map<String,Object> bookMap = new HashMap<>();

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

        categories.addAll(List.of("Technology", "Python", "Computer Programming","code","Maths","Science","Logic","Engineering","py"));

        List<Map<String, Object>> booksInMap = new ArrayList<>();
        Map<String,Object> bookToInsert = new HashMap<>();
        bookToInsert.put("id", "3030168779X");
        bookToInsert.put("title", "Programming for Computations - Python");
        bookToInsert.put("subtitle", Optional.of("A Gentle Introduction to Numerical Simulations with Python 3.6"));
        bookToInsert.put("description", "This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...");
        bookToInsert.put("authors", "Svein Linge, Hans Petter Langtangen");
        bookToInsert.put("publisher", "Springer");
        bookToInsert.put("pages", 350);
        bookToInsert.put("year", 2020);
        bookToInsert.put("image", "https://www.dbooks.org/img/books/3030168778s.jpg");
        bookToInsert.put("price", 30.99);
        bookToInsert.put("priceWithDiscount", 23.99);
        bookToInsert.put("categories", Arrays.asList(
                "Computational science and engineering",
                "Computer science--mathematics",
                "Computer software",
                "Mathematical software",
                "Mathematics",
                "Numeric computing",
                "Numerical analysis"
        ));
        for(int i = 0; i<20; i++){
        booksInMap.add(bookToInsert);
        }

        bookMap.put("status","ok");
        bookMap.put("total",1);
        bookMap.put("books",booksInMap);
    }

}
