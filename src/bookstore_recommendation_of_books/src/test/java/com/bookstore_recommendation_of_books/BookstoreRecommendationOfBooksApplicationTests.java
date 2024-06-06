package com.bookstore_recommendation_of_books;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookstoreRecommendationOfBooksApplicationTests {

	@Test
	void contextLoads(){
		BookstoreRecommendationOfBooksApplication.main(new String[]{});
		ConfigurableApplicationContext context = BookstoreRecommendationOfBooksApplication.context;
		assertEquals("[test]", Arrays.toString(context.getEnvironment().getActiveProfiles()));
	}

}
