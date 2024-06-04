package com.bookstore_recommendation_of_books.service;

import com.bookstore_recommendation_of_books.Data;
import com.bookstore_recommendation_of_books.model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.bookstore_recommendation_of_books.service.RecommendBooksService.getBooksCategories;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * This class is used to test the RecommendBooksService class.
 * It uses Mockito to mock the RestTemplate and the RecommendBooksService.
 */
@SpringBootTest
public class RecommendBooksServiceTest {

    @Autowired
    RecommendBooksService recommendBooksService;

    @MockBean
    static RestTemplate restTemplate;


    private static Data data = new Data();

    /**
     * This method sets up the mocked RestTemplate before each test.
     * It configures the RestTemplate to return predefined data when its methods are called.
     */
    @BeforeEach
    void setUp(){
        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> toInsert = new HashMap<>();
        toInsert.put("categories",data.categories);
        expected.add(toInsert);

        when(restTemplate.getForObject(eq("http://bookstore:8383/api/bookstore"), eq(List.class))).thenReturn(expected);

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenAnswer(new Answer<Map>() {
            @Override
            public Map answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String url = (String) args[0];
                if (url.startsWith("https://www.dbooks.org/api/search/")) {
                    return data.bookMap;
                }
                else if(url.startsWith("https://www.dbooks.org/api/book/")){
                    List<Map<String, Object>> obj = (List<Map<String, Object>>) data.bookMap.get("books");

                    return obj.getFirst();

                }
                else {
                    return null;
                }
            }
        });

    }

    @Test
    void getCategories(){

        List<String> categories = getBooksCategories();

        assertEquals("Technology",categories.getFirst());
        verify(restTemplate).getForObject(eq("http://bookstore:8383/api/bookstore"), eq(List.class));
    }

    @Test
    void peekCategories() {

        String[] categories = RecommendBooksService.peekCategories();

        assertEquals(8, categories.length);

        verify(restTemplate).getForObject(anyString(),eq(List.class));
    }

    @Test
    void getBooksIdToRecommend() {

        List<String> booksId = RecommendBooksService.getBooksIdToRecommend();

        assertEquals("3030168779X", booksId.getFirst());

        verify(restTemplate,times(8)).getForObject(anyString(),eq(Map.class));
    }

    @Test
    void recommendedBooksAndApplyDiscounts() {

        List<Book> recommendedBooks = RecommendBooksService.recommendedBooksAndApplyDiscounts();

        assertEquals("Programming for Computations - Python", recommendedBooks.getFirst().getTitle());
        assertEquals("3030168779", recommendedBooks.getFirst().getId());

        verify(restTemplate, times(16)).getForObject(anyString(),eq(Map.class));
    }

}
