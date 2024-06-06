package com.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class BookstoreApplicationTests {



	@Test
	void contextLoads() {
		BookstoreApplication.main(new String[]{});
		ConfigurableApplicationContext context = BookstoreApplication.context;
		assertEquals("[test]", Arrays.toString(context.getEnvironment().getActiveProfiles()));
	}



}
