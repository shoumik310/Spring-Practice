package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.model.Order;

import java.util.Set;

public interface OrderService {

    Order createOrder(Order order);

    Set<Order> fetchAllOrders();

    Order findByOrderId(long orderId);
}