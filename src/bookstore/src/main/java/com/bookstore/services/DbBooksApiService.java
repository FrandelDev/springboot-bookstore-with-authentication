package com.bookstore.services;


import com.bookstore.mappers.BookMapper;
import com.bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * This service class is used to interact with the dbooks.org API for operations related to books.
 * It uses RestTemplate for making HTTP requests.
 */
@Service
public class DbBooksApiService {
    static RestTemplate template = new RestTemplate();
        @Autowired
        public  DbBooksApiService(RestTemplate template){
            DbBooksApiService.template = template;
        }


    /**
     * This method is used to search for books by a given criteria.
     * It makes a request to the dbooks.org API and retrieves a list of books that match the search criteria.
     *
     * @param searchCriteria The criteria to search for books.
     * @return A list of books that match the search criteria.
     */
    public static List<Book> getBooksBySearch(String searchCriteria){
        List<String> ids = getIdOfBooks("https://www.dbooks.org/api/search/"+searchCriteria.toLowerCase().replaceAll(" ","+"),20);
        List<Book> books = new ArrayList<>();

        ids.forEach(id ->{
            Map<String,Object> book = template.getForObject("https://www.dbooks.org/api/book/"+id, Map.class);
            Book newBook = Book.builder()
                    .id(id)
                    .title(book.get("title").toString())
                    .subtitle(Optional.of(book.get("subtitle").toString()))
                    .description(book.get("description").toString())
                    .authors(book.get("authors").toString())
                    .publisher(book.get("publisher").toString())
                    .pages(Integer.valueOf(book.get("pages").toString()))
                    .year(Integer.valueOf(book.get("year").toString()))
                    .image(book.get("image").toString())
                    .price(Math.round(new Random().nextDouble(15.00,50.99)*100.0)/100.0)
                    .categories(AddCategoriesService.addCategories(book.get("title").toString()))
                    .build();
            books.add(newBook);
    });
        return books;
    }


    /**
     * This method is used to retrieve the most recent books.
     * It makes a request to the dbooks.org API and retrieves a list of the most recent books.
     *
     * @return A list of the most recent books.
     */
    public  static  List<Book> getRecentBooks(){
        List<String> ids = getIdOfBooks("https://www.dbooks.org/api/recent",20);
        List<Book> recentBooks = new ArrayList<>();

        ids.forEach(id ->{
            Map<String,Object> book = template.getForObject("https://www.dbooks.org/api/book/"+id, Map.class);
            Book newBook = Book.builder()
                    .id(id)
                    .title(book.get("title").toString())
                    .subtitle(Optional.of(book.get("subtitle").toString()))
                    .description(book.get("description").toString())
                    .authors(book.get("authors").toString())
                     .publisher(book.get("publisher").toString())
                    .pages(Integer.valueOf(book.get("pages").toString()))
                    .year(Integer.valueOf(book.get("year").toString()))
                    .image(book.get("image").toString())
                    .price(Math.round(new Random().nextDouble(15.00,50.99)*100.0)/100.0)
                    .categories(AddCategoriesService.addCategories(book.get("title").toString()))
                    .build();
            recentBooks.add(newBook);


        });
    return recentBooks;
    }


    /**
     * This method is used to retrieve the IDs of books from the dbooks.org API.
     * It makes a request to the dbooks.org API and retrieves a list of book IDs.
     *
     * @param url The URL to make the API request.
     * @param quantity The number of book IDs to retrieve.
     * @return A list of book IDs.
     */
    public static List<String> getIdOfBooks(String url, int quantity){
        List<String> ids = new ArrayList<>();

        Map<String,Object> result = template.getForObject(url, Map.class);
        List<Map<String,Object>> books = (List<Map<String,Object>>) result.get("books");

        for (int i = 0; i < quantity; i++) {
            StringBuilder purifierId = new StringBuilder(String.valueOf(books.get(i).get("id")));
            if (purifierId.charAt(purifierId.length() - 1) == 'X') {
                purifierId.deleteCharAt(purifierId.length() - 1);
            }

            ids.add(purifierId.toString());
        }

        return ids;
    }
}
