package com.bajajfinserve.orders.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTests {
	
	@Test
	void testNoArgsConstructor() {
		Order order = Order.builder().build();
		
		Assertions.assertNotNull(order);	
	}
	
	@Test
	void testAllArgsConstructor() {
		Order order = Order.builder().name("vinay").email("vinay@gmail.com").orderDate(LocalDate.now())
				.creditCardNumber("11223344").build();
		
		Assertions.assertNotNull(order);
		Assertions.assertEquals("vinay", order.getName());
		Assertions.assertEquals("vinay@gmail.com", order.getEmail());
		Assertions.assertEquals(LocalDate.now(), order.getOrderDate());
		Assertions.assertEquals("11223344", order.getCreditCardNumber());
		Assertions.assertNull(order.getLineItems());
	}
	

	@Test
	void testsetName() {
		Order order = Order.builder().name("vinay").email("vinay@gmail.com").orderDate(LocalDate.now())
				.creditCardNumber("11223344").build();
		
		order.setName("Harish");
		Assertions.assertNotNull(order);
		Assertions.assertEquals("Harish", order.getName());
	}
	
	@Test
	void addLineItem() {
		Order order = Order.builder().build();

		Assertions.assertNull(order.getLineItems());
		
		LineItem item = LineItem.builder().name("product1").price(400).qty(23).build();
		order.addLineItem(item);
		Assertions.assertNotNull(order.getLineItems());
		Assertions.assertTrue(!order.getLineItems().isEmpty());
		Assertions.assertEquals(1, order.getLineItems().size());
	}
	
	

}
