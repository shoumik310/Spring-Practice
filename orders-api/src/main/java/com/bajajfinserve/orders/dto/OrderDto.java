package com.bajajfinserve.orders.dto;

import java.time.LocalDate;

public interface OrderDto {

	String getName();

	String getEmail();

	LocalDate getOrderDate();

	double getPrice();
}
