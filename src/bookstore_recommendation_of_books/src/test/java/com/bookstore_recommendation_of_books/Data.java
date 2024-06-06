package com.bookstore_recommendation_of_books;
import com.bookstore_recommendation_of_books.model.Book;

import java.util.*;

public class Data {
    public List<Book> allBooks = new ArrayList<>();
    public List<String> categories = new ArrayList<>();
    public Map<String,Object> bookMap = new HashMap<>();

    public Data() {
        allBooks.add( new Book()
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
                .build());

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
        booksInMap.add(bookToInsert);

        bookMap.put("status","ok");
        bookMap.put("total",1);
        bookMap.put("books",booksInMap);
    }
}
