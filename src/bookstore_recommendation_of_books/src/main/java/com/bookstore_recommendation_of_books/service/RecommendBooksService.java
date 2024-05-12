package com.bookstore_recommendation_of_books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class RecommendBooksService {

    private static RestTemplate template = new RestTemplate();



    public static void recommendedBooksAndApplyDiscounts(){


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
