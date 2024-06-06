package com.bookstore_recommendation_of_books.model;

import com.bookstore_recommendation_of_books.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
private Data data = new Data();
    @Test
    void testToString() {
        System.out.println(data.allBooks.getFirst().toString());
        String expected = "Book{id='3030168779', title='Programming for Computations - Python', subtitle=Optional[A Gentle Introduction to Numerical Simulations with Python 3.6], description='This book presents computer programming as a key method for solving mathematical problems. This second edition of the well-received book has been extensively revised: All code is now written in Python...', authors='Svein Linge, Hans Petter Langtangen', publisher='Springer', pages=350, year=2020, image='https://www.dbooks.org/img/books/3030168778s.jpg', price=30.99, priceWithDiscount=23.99, categories=[Computational science and engineering, Computer science--mathematics, Computer software, Mathematical software, Mathematics, Numeric computing, Numerical analysis]}";
        assertEquals(expected,data.allBooks.getFirst().toString());
    }
}