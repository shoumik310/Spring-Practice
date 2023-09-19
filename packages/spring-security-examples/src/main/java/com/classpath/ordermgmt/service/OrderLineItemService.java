package com.classpath.ordermgmt.service;

import com.classpath.ordermgmt.exception.OrderLineItemNotFoundException;
import com.classpath.ordermgmt.model.OrderLineItem;

import java.util.List;

public interface OrderLineItemService {

    OrderLineItem saveOrderLineItem(OrderLineItem item);

    OrderLineItem updateOrderLineItem(long itemId, OrderLineItem item);

    List<OrderLineItem> fetchAllOrderLineItems();

    OrderLineItem findOrderLineItemById(long itemId) throws OrderLineItemNotFoundException;

    void deleteOrderLineItemById(long itemId);

}