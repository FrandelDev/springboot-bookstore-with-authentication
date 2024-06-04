package com.bookstore.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


/**
 * This service class is used to add categories to a book based on its title.
 * It makes a request to the Open Library API and retrieves a list of subjects related to the book.
 * If no subjects are found, it defaults to the category "Technology".
 */
@Service
public class AddCategoriesService {

    private static int chances = 32;

    private static final RestTemplate restTemplate = new RestTemplate();


    /**
     * This method is used to add categories to a book based on its title.
     * It makes a request to the Open Library API and retrieves a list of subjects related to the book.
     * If no subjects are found, it defaults to the category "Technology".
     *
     * @param title The title of the book.
     * @return A list of categories related to the book.
     */
    public static List<String> addCategories(@NotNull String title){

        String searchCriteria = title.toLowerCase().replaceAll(" ","+");
        String url = "https://openlibrary.org/search.json?title="+searchCriteria;

        Map<String,Object> res = restTemplate.getForObject(url, Map.class);

        int itemsFounded = (int)res.get("numFound");

        if(itemsFounded != 0){

            int randomCategories = new Random().nextInt(0, Math.min(itemsFounded, 100));

            List<Map<String, Object>> listOfObjects = (List<Map<String, Object>>) res.get("docs");


            Optional<List<String>> verify =  Optional.ofNullable((List<String>)listOfObjects.get(randomCategories).get("subject"));
            if(verify.isPresent()){
                return (List<String>) listOfObjects.get(randomCategories).get("subject");
            }
            else {
                chances--;
                if(chances > 0){
                  return addCategories(searchCriteria);
                }
            }

        }
            return List.of("Technology");
    }
}
