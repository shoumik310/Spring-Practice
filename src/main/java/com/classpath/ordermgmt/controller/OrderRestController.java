package com.classpath.ordermgmt.controller;

import com.classpath.ordermgmt.model.Order;
import com.classpath.ordermgmt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return this.orderService.createOrder(order);
    }

    @GetMapping
    public Set<Order> fetchOrders(){
        return this.orderService.fetchAllOrders();
    }
}