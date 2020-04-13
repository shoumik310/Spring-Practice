package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.exception.OrderLineItemNotFoundException;
import com.classpath.ordermgmt.model.OrderLineItem;
import com.classpath.ordermgmt.repository.OrderLineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineItemServiceImpl implements OrderLineItemService {

    private final OrderLineItemRepository itemRepository;

    @Autowired
    public OrderLineItemServiceImpl(OrderLineItemRepository itemDAO){
        this.itemRepository = itemDAO;
    }

    @Override
    public OrderLineItem saveOrderLineItem(OrderLineItem item) {
        return this.itemRepository.save(item);
    }

    @Override
    public OrderLineItem updateOrderLineItem(long itemId, OrderLineItem item) {
        return this.itemRepository.save(item);
    }

    @Override
    public List<OrderLineItem> fetchAllOrderLineItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public OrderLineItem findOrderLineItemById(long itemId) throws OrderLineItemNotFoundException {
        return this.itemRepository.findById(itemId)
                .orElseThrow(() ->  new OrderLineItemNotFoundException("Item with "+itemId+ " is not present"));
    }

    @Override
    public void deleteOrderLineItemById(long itemId) {
        this.itemRepository.deleteById(itemId);
    }
}