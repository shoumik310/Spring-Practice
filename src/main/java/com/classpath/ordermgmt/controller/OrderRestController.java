package com.classpath.ordermgmt.controller;

import com.classpath.ordermgmt.exception.ResourceNotFoundException;
import com.classpath.ordermgmt.model.Order;
import com.classpath.ordermgmt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/v1/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order){
        return this.orderService.createOrder(order);
    }

    @GetMapping
    public Set<Order> fetchOrders(){
        return this.orderService.fetchAllOrders();
    }

    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable long orderId, @Valid @RequestBody Order updateOrder){
        return this.orderService.updateOrder(orderId, updateOrder);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrderById(@PathVariable long orderId){
        this.orderService.deleteOrderById(orderId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderByOrderId(@PathVariable long orderId) throws ResourceNotFoundException {
        System.out.println("Inside the get Order by Order id "+orderId);
        return this.orderService.findByOrderId(orderId);
    }
}