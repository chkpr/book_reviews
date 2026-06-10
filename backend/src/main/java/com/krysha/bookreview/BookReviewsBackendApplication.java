package com.krysha.bookreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookReviewsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewsBackendApplication.class, args);
	}

}
