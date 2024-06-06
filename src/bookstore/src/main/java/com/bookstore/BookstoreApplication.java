package com.bookstore;


import com.bookstore.services.AddCategoriesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class BookstoreApplication {
	static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(BookstoreApplication.class, args);

	}
}
