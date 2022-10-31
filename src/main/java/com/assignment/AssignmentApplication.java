package com.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		LocalDateTime a = LocalDateTime.now();
		SpringApplication.run(AssignmentApplication.class, args);
		System.out.println("Hello world");
		LocalDateTime b = LocalDateTime.now();
		System.out.println(a + " " + b);
		System.out.println(a.isAfter(b));
	}

}
