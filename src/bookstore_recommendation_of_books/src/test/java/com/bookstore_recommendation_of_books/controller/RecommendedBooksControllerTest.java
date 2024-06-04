package com.bookstore_recommendation_of_books.controller;

import com.bookstore_recommendation_of_books.Data;
import com.bookstore_recommendation_of_books.service.RecommendBooksService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * This class is used to test the RecommendedBooksController class.
 * It uses MockMvc to perform HTTP requests and Mockito to mock the RecommendBooksService.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RecommendedBooksControllerTest {
    @Autowired
    private MockMvc mvc;

    private Data data = new Data();


    /**
     * This test checks if the getRecommendedBooks method of the RecommendedBooksController works correctly.
     * It mocks a RecommendBooksService and checks if the RecommendedBooksController correctly retrieves the recommended books.
     */
    @Test
    void getRecommendedBooks() throws Exception {
        MockedStatic<RecommendBooksService> recommendBooksServiceMockedStatic = Mockito.mockStatic(RecommendBooksService.class);
        recommendBooksServiceMockedStatic.when(RecommendBooksService::recommendedBooksAndApplyDiscounts).thenReturn(data.allBooks);

        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Programming for Computations - Python"));
    }
}