package com.bookstore.services;

import com.bookstore.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


/**
 * This class is used to test the AddCategoriesService class.
 * It uses Mockito to mock the RestTemplate and the AddCategoriesService.
 */
@SpringBootTest
class AddCategoriesServiceTest {

@MockBean
private RestTemplate template;

private Data data = new Data();

@Autowired
private  AddCategoriesService addCategoriesService;

    @Test
    void addCategories_NoItemsFounded() {
        List<String> result = addCategoriesService.addCategories(".");
        assertEquals("Technology",result.getFirst());
    }
    @Test
    void addCategoriesTest() {
        List<String> result = addCategoriesService.addCategories("java");
        assertNotNull(result);
    }



}