package com.bajajfinserve.orders.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Map<String, Object> fetchOrders(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			@RequestParam(name = "sort", defaultValue = "asc", required = false) String direction,
			@RequestParam(name = "field", defaultValue = "name", required = false) String property) {
		return this.orderService.fetchAll(page, size, direction, property);
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

	@GetMapping("/price")
	public Map<String, Object> fetchOrdersByPriceRange(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			@RequestParam(name = "min", defaultValue = "400", required = false) double min,
			@RequestParam(name = "max", defaultValue = "2000", required = false) double max) {
		return this.orderService.findOrdersByPriceRange(page, size, min, max);
	}
}
