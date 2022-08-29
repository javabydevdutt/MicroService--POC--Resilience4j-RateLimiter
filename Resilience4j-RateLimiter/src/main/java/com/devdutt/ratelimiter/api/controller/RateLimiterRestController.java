package com.devdutt.ratelimiter.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/rateLimiter")
public class RateLimiterRestController {

	Logger logger = LoggerFactory.getLogger(RateLimiterRestController.class);

	@GetMapping("/getMessage")
	@RateLimiter(name = "getMessageRateLimiter", fallbackMethod = "getMessageFallBackMethod")
	public ResponseEntity<String> getMessage(@RequestParam(value = "name", defaultValue = "dear") String name) {
		return ResponseEntity.ok().body("Hello..[" + name
				+ "], Welcome to MicroSerivce Rest Application using Resilience4j-Rate Limiters implementation");
	}

	public ResponseEntity<String> getMessageFallBackMethod(RequestNotPermitted exception) {
		logger.info("Rate limit has applied, So no further calls are getting accepted");

		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.body("Too many request: No Future request will be accepted...!, Please try after some time");
	}
}
