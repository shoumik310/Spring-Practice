package com.bajajfinserve.orders.util;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bajajfinserve.orders.dao.OrderJpaRepository;
import com.bajajfinserve.orders.model.LineItem;
import com.bajajfinserve.orders.model.Order;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile({"qa", "dev"})
public class BootstrapAppData {
	
	private final OrderJpaRepository orderRepository;
	private final Faker faker = new Faker();
	
	@Value("${app.orderCount}")
	private int orderCount;
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationStartup(ApplicationReadyEvent event) {
		System.out.println("Application is ready :: ");
		//Order order = new Order(1, "Arun", "Sagar", false, true, true, 21, 33);
		IntStream
			.range(0, orderCount)
			.forEach(index -> {
				String firstName = faker.name().firstName();
				Order order = Order.builder()
									.name(firstName)
									.email(firstName+"@"+faker.internet().domainName())
									.orderDate(faker.date().past(4, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
									.creditCardNumber(faker.business().creditCardNumber())
									.build();
				
				IntStream.range(0, 3).forEach(val -> {
					
					LineItem lineItem = LineItem.builder()
												.name(faker.commerce().productName())
												.qty(faker.number().numberBetween(2, 4))
												.price(faker.number().randomDouble(2, 400, 600))
												.build();
					order.addLineItem(lineItem);
				});
				
				double totalOrderPrice = order
											.getLineItems()
											.stream()
											// input - > list of lineItem [lineItem1, lineItem2, lineItem3]
											// output -> list of price [400, 600, 899]
											.map(lineItem -> lineItem.getQty() * lineItem.getPrice())
											.reduce(Double::sum)
											.orElse(0d);
				order.setPrice(totalOrderPrice);	
				this.orderRepository.save(order);
			});
		
	}
}
