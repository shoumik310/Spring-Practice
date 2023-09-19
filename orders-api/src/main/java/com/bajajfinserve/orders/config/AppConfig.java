package com.bajajfinserve.orders.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	/*
	 * @Value("${app.paymentgateway}") private String paymentGatewayUrl;
	 */
	@Bean
	public User user() {
		return new User();
	}
	
	@ConditionalOnProperty(prefix = "app", value = "loadUser", havingValue = "true", matchIfMissing = true)
	@Bean
	public User userBasedOnCondition() {
		return new User();
	}
	
	@ConditionalOnBean(name="userBasedOnCondition")
	@Bean
	public User userBasedOnBean() {
		return new User();
	}
	
	
	@ConditionalOnMissingBean(name="userBasedOnCondition")
	@Bean
	public User userBasedMissingBean() {
		return new User();
	}
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
	}
	@Bean
	public WebClient webClientPaymentGateway() {
		return WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
	}
}

class User {
	
}
