package com.example.cooperative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CooperativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooperativeApplication.class, args);
	}

}
