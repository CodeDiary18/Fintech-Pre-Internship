package com.example.finedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FineduApplication {

	public static void main(String[] args) {
		SpringApplication.run(FineduApplication.class, args);
	}

}
