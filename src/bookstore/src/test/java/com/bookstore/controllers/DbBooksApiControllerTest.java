package com.bookstore.controllers;

import com.bookstore.Data;
import com.bookstore.services.DbBooksApiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DbBooksApiControllerTest {

    @Autowired
    private MockMvc mvc;

    static Data data = new Data();

    @BeforeAll()
    static void setUp() {
        MockedStatic<DbBooksApiService> mockedService = Mockito.mockStatic(DbBooksApiService.class);
            mockedService.when(DbBooksApiService::getRecentBooks).thenReturn(data.allBooks);
            mockedService.when(()-> DbBooksApiService.getBooksBySearch(anyString())).thenReturn(data.allBooks);

    }

    @Test
    void getRecentBooks() throws Exception {

        mvc.perform(get("/recent").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Programming for Computations - Python"));

    }

    @Test
    void searchBooks() throws Exception {

            mvc.perform(get("/search?criteria=search-criteria").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].title").value("Programming for Computations - Python"));
        }

}