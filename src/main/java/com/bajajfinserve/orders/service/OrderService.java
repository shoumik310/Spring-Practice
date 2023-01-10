package com.bajajfinserve.orders.service;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	public Map<String, Object> fetchAll(int page, int size, String strDirection, String property) {
		// if you are using Java 11
		// return Set.copyOf(orderRepository.findAll());

		Sort.Direction direction = strDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC: Sort.Direction.DESC;
		PageRequest pageRequest = PageRequest.of(page, size, direction, property);
		
		Page<Order> pageResponse = this.orderRepository.findAll(pageRequest);
		
		long totalRecords = pageResponse.getTotalElements();
		int pages = pageResponse.getTotalPages();
		Set<Order> data = pageResponse.toSet();
		
		
		Map<String, Object> responseMap = new LinkedHashMap<>();
		
		responseMap.put("total-records", totalRecords);
		responseMap.put("pages", pages);
		responseMap.put("content", data);
		
		return responseMap;
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
