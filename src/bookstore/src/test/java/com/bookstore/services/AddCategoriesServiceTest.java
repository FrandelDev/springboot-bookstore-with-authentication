package com.bookstore.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This class is used to test the AddCategoriesService class.
 * It uses Mockito to mock the RestTemplate and the AddCategoriesService.
 */
@ExtendWith(MockitoExtension.class)
class AddCategoriesServiceTest {

@Mock
private RestTemplate template;

@InjectMocks
private  AddCategoriesService addCategoriesService;

    @Test
    void addCategories_NoItemsFounded() {
        List<String> result = addCategoriesService.addCategories(".");
        assertEquals("Technology",result.getFirst());
    }
}