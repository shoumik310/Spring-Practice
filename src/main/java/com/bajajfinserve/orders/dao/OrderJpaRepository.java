package com.bajajfinserve.orders.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bajajfinserve.orders.model.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
	
	Page<Order> findByPriceBetween(double minPrice, double maxPrice, Pageable pageRequest);

}
