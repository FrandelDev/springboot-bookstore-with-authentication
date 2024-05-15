package com.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver;


import java.util.*;

@Service
public class AddCategoriesService {

    private static int chances = 32;
    private static final RestTemplate restTemplate = new RestTemplate();


    public static List<String> addCategories(String title){

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
