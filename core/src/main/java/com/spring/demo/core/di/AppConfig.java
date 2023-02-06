package com.spring.demo.core.di;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public Role adminRole() {
		return new Role("ADMIN_ROLE");
	}

	// Constructor Injection
	@Bean
	public User adminUser() {
		return new User(adminRole());
	}

	// Setter Injection
	@Bean
	public User adminUser2() {
		User user = new User();
		user.setRole(adminRole());
		return user;
	}

	// Generic List Injection
	@Bean
	public List<String> orderIds() {
		return Arrays.asList("id1", "id2", "id3");
	}

//	@Bean
//	@Primary
//	public List<String> orderId() {
//		return Arrays.asList("id4", "id5", "id6");
//	}

	// List injection using constructor
	@Bean
	public UserWList userWithList() {
		return new UserWList(orderIds());
	}

	//Autowiring test
	@Bean
	public Integer randInt() {
		return 100;
	}
	
	@Bean
	public List<Integer> ints(){
		return Arrays.asList(5,6,7,8,9);
	}

	
}
