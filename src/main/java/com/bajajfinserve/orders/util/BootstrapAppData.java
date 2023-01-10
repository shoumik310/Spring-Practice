package com.bajajfinserve.orders.util;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bajajfinserve.orders.dao.OrderJpaRepository;
import com.bajajfinserve.orders.model.Order;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final OrderJpaRepository orderRepository;
	private final Faker faker = new Faker();
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationStartup(ApplicationReadyEvent event) {
		System.out.println("Application is ready :: ");
		//Order order = new Order(1, "Arun", "Sagar", false, true, true, 21, 33);
		IntStream
			.range(0, 10)
			.forEach(index -> {
				String firstName = faker.name().firstName();
				Order order = Order.builder()
									.name(firstName)
									.email(firstName+"@"+faker.internet().domainName())
									.dob(faker.date().past(4, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
									.build();
				this.orderRepository.save(order);
			});
		
	}
}
