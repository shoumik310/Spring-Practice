package com.bajajfinserve.orders.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bajajfinserve.orders.model.Order;
import com.bajajfinserve.orders.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {
	
	private final OrderService orderService;
	
	@GetMapping
	public Set<Order> fetchOrders(){
		return this.orderService.fetchAll();
	}
	
	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable("id") long orderId) {
		return this.orderService.findById(orderId);
	}
	
	@PostMapping
	public Order saveOrder(@RequestBody Order order) {
		return this.orderService.save(order);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable("id") long orderId) {
		this.orderService.deleteById(orderId);
	}
}
