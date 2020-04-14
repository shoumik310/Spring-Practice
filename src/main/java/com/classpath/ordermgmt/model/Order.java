package com.classpath.ordermgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private long orderId;

    private double price;

    @OneToMany(mappedBy = "order",
            fetch = EAGER,
            cascade = CascadeType.ALL )
    @JsonProperty("order_line_items")
    @Column(name = "line_items")
    private Set<OrderLineItem> orderLineItems;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(Set<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    // scaffolding code
    public void addOrderLineItem(OrderLineItem orderLineItem){
        this.orderLineItems.add(orderLineItem);
        orderLineItem.setOrder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", price=" + price +
                ", orderLineItems=" + orderLineItems +
                '}';
    }
}