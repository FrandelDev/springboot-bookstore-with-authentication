package com.bookstore_recommendation_of_books;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookstoreRecommendationOfBooksApplicationTests {

	@Test
	void contextLoads(){
		BookstoreRecommendationOfBooksApplication.main(new String[]{});
		ConfigurableApplicationContext context = BookstoreRecommendationOfBooksApplication.context;
		assertEquals("[test]", Arrays.toString(context.getEnvironment().getActiveProfiles()));
	}

}
