package com.bookstore_recommendation_of_books;

import com.bookstore_recommendation_of_books.service.RecommendBooksService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookstoreRecommendationOfBooksApplication {
	static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(BookstoreRecommendationOfBooksApplication.class, args);

	}


}
