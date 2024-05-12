package com.bookstore_recommendation_of_books.service;


import com.bookstore_recommendation_of_books.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class RecommendBooksService {

    private static final RestTemplate template = new RestTemplate();



    public static List<Book> recommendedBooksAndApplyDiscounts(){

        List<String> booksId = getBooksIdToRecommend();
        List<Book> booksToRecommend = new ArrayList<>();
        for (String id : booksId) {
                StringBuilder purifierId = new StringBuilder(id);
            if (id.charAt(id.length() - 1) == 'X') {
                purifierId.deleteCharAt(purifierId.length() - 1);
            }

            Map<String,Object> searchBook = template.getForObject("https://www.dbooks.org/api/book/"+purifierId,Map.class);
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
                    .price(Math.round(new Random().nextDouble(15.00,50.99)*100.0)/100.0)
                    .priceWithDiscount(Math.round((newBook.getPrice()-(newBook.getPrice()*10)/100)*100d)/100d)
                    .categories((List<String>) template.getForObject("http://localhost:8383/api/bookstore/get-categories?bookTitle="+newBook.getTitle(),List.class))
                    .build();

            booksToRecommend.add(newBook);

        }
     return booksToRecommend;
    }

    public static List<String> getBooksIdToRecommend(){
        var categories = Arrays.asList(peekCategories());
        List<String> booksId = new ArrayList<>();

        categories.forEach(category ->{
            Map<String,Object> res = template.getForObject("https://www.dbooks.org/api/search/"+category.toLowerCase().replaceAll(" ","+"),Map.class);

            String total =  res.get("total").toString();
            if(res.get("status") != "not found"){
            List<Map<String,Object>> books = (List<Map<String,Object>>) res.get("books");
            booksId.add((String) books.get(new Random().nextInt(0, Integer.valueOf(total))).get("id"));
            }
        });

        return booksId;
    }

    public static String[]  peekCategories(){
        var categories = getBooksCategories();
        String[] categoriesForSearchCriteria = new String[7];

        for (int i = 6; i >= 0; i--){
            int categoryIndex = new Random().nextInt(0,categories.toArray().length);
            categoriesForSearchCriteria[i] = categories.get(categoryIndex);
        }
        return categoriesForSearchCriteria;
    }

    public static List<String> getBooksCategories(){

        List<String> allCategories = new ArrayList<>();
        List<Map<String, Object>> result = template.getForObject("http://localhost:8383/api/bookstore", List.class);

        result.forEach(book ->{
          allCategories.addAll((List<String>)book.get("categories"));
        });

        return allCategories;

    }
}
