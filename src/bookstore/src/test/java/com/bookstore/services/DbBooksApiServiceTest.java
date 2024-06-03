package com.bookstore.services;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DbBooksApiServiceTest {

    @Autowired
    DbBooksApiService service;

    @MockBean
    RestTemplate template;


    @Test
    void getIdOfBooks() {
       Map<String,Object> setIdsExpected = new HashMap<>();
        List<Map<String,Object>> idsExpected = new ArrayList<>();
       setIdsExpected.put("books",idsExpected);
       Map<String,Object> item = new HashMap<>();
       item.put("id","123X");
       idsExpected.add(item);

        when(template.getForObject(anyString(),eq(Map.class))).thenReturn(setIdsExpected);

        List<String> ids = DbBooksApiService.getIdOfBooks("any",1);

        assertEquals("123",ids.getFirst());
    verify(template).getForObject(anyString(),eq(Map.class));
    }
}