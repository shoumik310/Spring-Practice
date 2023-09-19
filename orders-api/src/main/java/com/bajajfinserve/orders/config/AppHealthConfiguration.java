package com.bajajfinserve.orders.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.bajajfinserve.orders.dao.OrderJpaRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
class DBHealthEndpoint implements HealthIndicator{

	private final OrderJpaRepository orderRepository;
	
	@Override
	public Health health() {
		long totalOrderCount = this.orderRepository.count();
		if(totalOrderCount >= 0) {
			return Health.status(Status.UP).withDetail("DB-status", "DB is up").build();
		}
		return Health.status(Status.DOWN).withDetail("DB-status", "DB is down").build();
	}

}

@Configuration
@RequiredArgsConstructor
class PaymentGatewayEndpoint implements HealthIndicator{

	private final WebClient webClient;

	@Override
	public Health health() {
		String response = this.webClient.get().uri("/users").exchangeToMono(res -> res.bodyToMono(String.class)).block();
		if(response != null && !response.isEmpty()) {
			return Health.status(Status.UP).withDetail("Payment-gateway", "Payment gateway is up").build();
		}
		return Health.status(Status.DOWN).withDetail("Payment-gateway", "Payment gateway is down").build();
	}

}
