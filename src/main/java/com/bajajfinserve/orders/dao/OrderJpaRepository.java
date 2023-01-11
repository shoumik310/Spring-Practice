package com.bajajfinserve.orders.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bajajfinserve.orders.model.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
	
	List<Order> 
	findByPriceBetween(double minPrice, double maxPrice);

}
