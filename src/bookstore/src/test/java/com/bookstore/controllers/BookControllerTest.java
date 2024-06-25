package com.bookstore.controllers;

import com.bookstore.Data;
import com.bookstore.models.Book;
import com.bookstore.services.AddCategoriesService;
import com.bookstore.services.DatabaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * This class is used to test the BookController class.
 * It uses MockMvc to perform HTTP requests and Mockito to mock the DatabaseService.
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DatabaseService databaseService;


    private Data data = new Data();

    @Test
    void getBook() throws Exception {
        when(databaseService.getBookById("3030168779")).thenReturn(data.allBooks.getFirst());
        mvc.perform(get("/book/3030168779").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Programming for Computations - Python"));
        verify(databaseService).getBookById("3030168779");
    }
    @Test
    void setBook() throws Exception {

        Book book = data.allBooks.getFirst();

        when(databaseService.insertNewBook(book,"admin")).thenReturn(1);

        ObjectMapper bookAsJson = new ObjectMapper();
        bookAsJson.registerModule(new Jdk8Module());  // Esto permite que ObjectMapper maneje correctamente los Optionals
        String bookJson = bookAsJson.writeValueAsString(book);

        mvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated());

        verify(databaseService, times(1)).insertNewBook(book,"admin");
    }

    /**
     * This test checks if the setBook method of the BookController can handle incomplete books correctly.
     */
    @Test
    void setIncompleteBook() throws Exception {
        Book book = data.allBooks.get(1);
        ObjectMapper bookAsJson = new ObjectMapper();
        bookAsJson.registerModule(new Jdk8Module());
        String bookJson = bookAsJson.writeValueAsString(book);

       MvcResult result= mvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper jsonAsBook = new ObjectMapper();
        jsonAsBook.registerModule(new Jdk8Module());

        Book completeBook = jsonAsBook.readValue(responseBody,Book.class);
        when(databaseService.insertNewBook(completeBook,"admin")).thenReturn(1);
        verify(databaseService, times(1)).insertNewBook(completeBook,"admin");
    }


    @Test
    void deleteBook() throws Exception {
        when(databaseService.removeBook(anyString())).thenReturn(true);

        mvc.perform(delete("/book/3030168779"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
        verify(databaseService).removeBook(anyString());
    }

    @Test
    void getAllBuyiedBooks() throws Exception {
        when(databaseService.getAllBooks("admin")).thenReturn(data.allBooks);
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Programming for Computations - Python"));
        verify(databaseService).getAllBooks("admin");
    }

    @Test
    void getCategories() throws Exception {
        try (MockedStatic<AddCategoriesService> mocked = Mockito.mockStatic(AddCategoriesService.class)) {
            mocked.when(() -> AddCategoriesService.addCategories("Programming for Computations - Python")).thenReturn(data.categories);

            mvc.perform(get("/get-categories?bookTitle=Programming for Computations - Python").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").value("Technology"))
                    .andExpect(jsonPath("$[1]").value("Python"));
        }
    }
}