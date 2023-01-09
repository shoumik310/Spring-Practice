package com.bajajfinserve.orders.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bajajfinserve.orders.dao.OrderJpaRepository;
import com.bajajfinserve.orders.model.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderJpaRepository orderRepository;

	public Order save(Order order) {
		return this.orderRepository.save(order);
	}

	public Set<Order> fetchAll() {
		// if you are using Java 11
		// return Set.copyOf(orderRepository.findAll());
		return new HashSet<Order>(orderRepository.findAll());
	}

	public Order findById(long orderId) {
		// avoid imperative programming

		/*
		 * Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
		 * if(optionalOrder.isPresent()) { return optionalOrder.get(); } throw new
		 * IllegalArgumentException("invalid order id passed");
		 */
		return this.orderRepository.findById(orderId)
				.orElseThrow(() -> new IllegalArgumentException("invalid order id passed"));
	}

	public void deleteById(long orderId) {
		this.orderRepository.deleteById(orderId);
	}

}
