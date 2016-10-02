package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
		System.setProperty("gate.home", "C:\\Program Files\\GATE_Developer_8.1");
		SpringApplication.run(SearchApplication.class, args);
	}
}
