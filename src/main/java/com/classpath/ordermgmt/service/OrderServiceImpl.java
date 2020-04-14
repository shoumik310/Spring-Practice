package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.model.Order;
import com.classpath.ordermgmt.model.OrderLineItem;
import com.classpath.ordermgmt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        System.out.println("Inside the save method of Order service .... :: ");
        System.out.println(order);
        for(OrderLineItem orderLineItem: order.getOrderLineItems()) {
            orderLineItem.setOrder(order);
        }

        return this.orderRepository.save(order);
    }

    @Override
    public Set<Order> fetchAllOrders() {
        return new HashSet<>(this.orderRepository.findAll());
    }

    @Override
    public Order findByOrderId(long orderId) {
        return this.orderRepository
                    .findById(orderId)
                    .orElseGet(()-> new Order());

    }


}