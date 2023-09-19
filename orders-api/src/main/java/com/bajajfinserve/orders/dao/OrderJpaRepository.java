package com.bajajfinserve.orders.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bajajfinserve.orders.dto.OrderDto;
import com.bajajfinserve.orders.model.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
	
	Page<OrderDto> findByPriceBetween(double minPrice, double maxPrice, Pageable pageRequest);
	
	@Query("select o from Order o where o.price > ?1 AND o.price < ?2")
	Page<Order> fetchAllOrdersBetweenPrice(double minPrice, double maxPrice, Pageable pageRequest);

}
