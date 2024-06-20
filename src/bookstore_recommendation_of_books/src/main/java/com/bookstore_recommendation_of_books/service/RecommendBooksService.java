package com.bookstore_recommendation_of_books.service;


import com.bookstore_recommendation_of_books.model.Book;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.*;


/**
 * This service class is used to recommend books and apply discounts.
 * It uses RestTemplate to make HTTP requests to the dbooks.org API and the bookstore API.
 */
@Service
public class RecommendBooksService {

    private static RestTemplate template;
    private static String jwtToken;

    @Autowired
    public RecommendBooksService(RestTemplate template) {
        RecommendBooksService.template = template;
    }

    private static <T> T getForObject(String url, Class<T> responseType) throws AuthenticationException {

        if (!jwtToken.startsWith("Bearer") ||jwtToken == null || jwtToken.isEmpty()) {
            throw new AuthenticationException("Not Valid Token");
        }

        // Set Authorization Header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);

        // Prepare Request
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Request
        ResponseEntity<T> response = template.exchange(url, HttpMethod.GET, request, responseType);

        return response.getBody();
    }


    /**
     * This method is used to recommend books and apply discounts.
     * It makes requests to the dbooks.org API to get book details and applies a discount to the price.
     *
     * @return A list of recommended books with applied discounts.
     */
    public static List<Book> recommendedBooksAndApplyDiscounts(String... jwtAuth) throws AuthenticationException {
        jwtToken = jwtAuth[0];
        List<String> booksId = getBooksIdToRecommend();
        List<Book> booksToRecommend = new ArrayList<>();

        for (String id : booksId) {
                StringBuilder purifierId = new StringBuilder(id);
            if (id.charAt(id.length() - 1) == 'X') {
                purifierId.deleteCharAt(purifierId.length() - 1);
            }

            Map<String,Object> searchBook = getForObject("https://www.dbooks.org/api/book/"+purifierId,Map.class);
            if(searchBook != null) {
                Book newBook = new Book();
                newBook
                        .id(purifierId.toString())
                        .title(searchBook.get("title").toString())
                        .subtitle(Optional.of(searchBook.get("subtitle").toString()))
                        .description(searchBook.get("description").toString())
                        .authors(searchBook.get("authors").toString())
                        .publisher(searchBook.get("publisher").toString())
                        .pages(Integer.valueOf(searchBook.get("pages").toString()))
                        .year(Integer.valueOf(searchBook.get("year").toString()))
                        .image(searchBook.get("image").toString())
                        .price(Math.round(new SecureRandom().nextDouble(15.00, 50.99) * 100.0) / 100.0)
                        .priceWithDiscount(Math.round((newBook.getPrice() - (newBook.getPrice() * 10) / 100) * 100d) / 100d)
                        .categories((List<String>) getForObject("http://bookstore:8383/api/bookstore/get-categories?bookTitle=" + newBook.getTitle(), List.class))
                        .build();

                booksToRecommend.add(newBook);
            }
        }
     return booksToRecommend;
    }


    /**
     * This method is used to get the IDs of books to recommend.
     * It makes requests to the dbooks.org API to search for books by category and retrieves their IDs.
     *
     * @return A list of book IDs to recommend.
     */
    public static List<String> getBooksIdToRecommend() throws AuthenticationException {
        var categories = Arrays.asList(peekCategories());
        List<String> booksId = new ArrayList<>();

        categories.forEach(category ->{
            Map<String,Object> res = template.getForObject("https://www.dbooks.org/api/search/"+category.toLowerCase().replace(" ","+"),Map.class);

            String total =  res.get("total").toString();
            if(res.get("status") != "not found"){
            List<Map<String,Object>> books = (List<Map<String,Object>>) res.get("books");
            booksId.add((String) books.get(new SecureRandom().nextInt(0, Integer.valueOf(total))).get("id"));
            }
        });

        return booksId;
    }

    /**
     * This method is used to get the IDs of books to recommend.
     * It makes requests to the dbooks.org API to search for books by category and retrieves their IDs.
     *
     * @return A list of book IDs to recommend.
     */
    public static String[]  peekCategories() throws AuthenticationException {
        List<String> categories = getBooksCategories();

        String[] categoriesForSearchCriteria = new String[8];

        for (int i = 7; i >= 0; i--){
            int categoryIndex = new SecureRandom().nextInt(0,categories.size());
            categoriesForSearchCriteria[i] = categories.get(categoryIndex);
        }

        return categoriesForSearchCriteria;
    }

    /**
     * This method is used to get all book categories.
     * It makes a request to the bookstore API and retrieves all book categories.
     *
     * @return A list of all book categories.
     */
    public static List<String> getBooksCategories() throws AuthenticationException {

        List<String> allCategories = new ArrayList<>();
        List<Map<String, Object>> result = getForObject("http://bookstore:8383/api/bookstore", List.class);

        if(result != null){
        result.forEach(book -> allCategories.addAll((List<String>)book.get("categories")));
        }

        return allCategories;

    }
}
