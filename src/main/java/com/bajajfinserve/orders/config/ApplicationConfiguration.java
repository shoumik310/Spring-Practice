package com.bajajfinserve.orders.config;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration implements CommandLineRunner {
	
	private final ApplicationContext applicationContext;
	
	public ApplicationConfiguration(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beanNames = this.applicationContext.getBeanDefinitionNames();
		Stream.of(beanNames).forEach(System.out::println);
	}

}
