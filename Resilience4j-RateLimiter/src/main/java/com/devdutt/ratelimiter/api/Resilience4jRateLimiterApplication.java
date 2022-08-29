package com.devdutt.ratelimiter.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Resilience4jRateLimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jRateLimiterApplication.class, args);
	}

}
