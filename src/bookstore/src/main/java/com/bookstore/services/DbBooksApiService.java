package com.bookstore.services;


import com.bookstore.mappers.BookMapper;
import com.bookstore.models.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DbBooksApiService {
    private static final RestTemplate template = new RestTemplate();

    public static List<Book> getBooksBySearch(String searchCriteria){
        List<String> ids = getIdOfBooks("https://www.dbooks.org/api/search/"+searchCriteria.toLowerCase().replaceAll(" ","+"));
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
    public  static  List<Book> getRecentBooks(){
        List<String> ids = getIdOfBooks("https://www.dbooks.org/api/recent");
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
    public static List<String> getIdOfBooks(String url){
        List<String> ids = new ArrayList<>();

        Map<String,Object> result = template.getForObject(url, Map.class);
        List<Map<String,Object>> books = (List<Map<String,Object>>) result.get("books");
        for (int i = 0; i < 20; i++) {
            StringBuilder purifierId = new StringBuilder(String.valueOf(books.get(i).get("id")));
            if (purifierId.charAt(purifierId.length() - 1) == 'X') {
                purifierId.deleteCharAt(purifierId.length() - 1);
            }

            ids.add(purifierId.toString());
        }




        return ids;
    }
}
