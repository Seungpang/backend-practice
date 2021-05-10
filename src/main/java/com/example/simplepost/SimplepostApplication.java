package com.example.simplepost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SimplepostApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplepostApplication.class, args);
	}

}
