package com.highwire.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class. Run this main method to start the application on port 8080.
 */
@SpringBootApplication
@ComponentScan("com.highwire.interview")
@EnableJpaRepositories(basePackages = {"com.highwire.interview"})
public class InterviewChallengeApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(InterviewChallengeApplication.class, args);
	}
}
