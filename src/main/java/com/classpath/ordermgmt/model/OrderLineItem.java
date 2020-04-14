package com.classpath.ordermgmt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "order_line_item")
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "price")
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    @JsonIgnore
    private Order order;

    //default constructor
    OrderLineItem(){}

    public OrderLineItem(String name, double price){
        this.name = name;
        this.price = price;
    }
}