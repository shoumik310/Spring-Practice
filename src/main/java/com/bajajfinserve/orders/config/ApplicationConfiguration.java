package com.bajajfinserve.orders.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello world with Spring Boot !!");
	}

}
