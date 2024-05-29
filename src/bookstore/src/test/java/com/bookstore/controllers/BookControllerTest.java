package com.bookstore.controllers;

import com.bookstore.Data;
import com.bookstore.services.DatabaseService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DatabaseService databaseService;


    private Data data = new Data();

    @Test
    @Disabled
    void getBook() {
    }
    @Test
    @Disabled
    void setBook() {
    }
    @Test
    @Disabled
    void deleteBook() {
    }

    @Test
    void getAllBuyiedBooks() throws Exception {
        when(databaseService.getAllBooks()).thenReturn(data.allBooks);
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Programming for Computations - Python"));
    }
    @Test
    @Disabled
    void getCategories() {
    }
}