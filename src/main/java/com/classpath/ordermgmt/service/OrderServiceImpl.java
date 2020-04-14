package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.exception.ResourceNotFoundException;
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
    public Order findByOrderId(long orderId) throws ResourceNotFoundException {
        return this.orderRepository
                    .findById(orderId)
                    .orElseThrow(()->new ResourceNotFoundException("Resource not found"));

    }

    @Override
    public Order updateOrder(long orderId, Order updateOrder) {
        return this.orderRepository.findById(orderId).map(order -> {
            order.setMerchantName(updateOrder.getMerchantName());
            order.setOrderDate(updateOrder.getOrderDate());
            return this.orderRepository.save(order);
        }).orElseGet(() -> {
            updateOrder.setOrderId(orderId);
            return this.orderRepository.save(updateOrder);
        });
    }

    @Override
    public void deleteOrderById(long orderId) {
        this.orderRepository.deleteById(orderId);
    }
}