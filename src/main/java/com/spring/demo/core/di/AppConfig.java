package com.spring.demo.core.di;

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
}
