package com.classpath.ordermgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "order_line_item")
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString(exclude = "order")
@EqualsAndHashCode(exclude = "price")
public class OrderLineItem extends AuditModel{

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    @NotEmpty(message = "name must not be empty")
    private String name;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    @JsonIgnore
    private Order order;

    //default constructor used for serialization and deseriazation
    OrderLineItem(){}

    public OrderLineItem(String name, double price){
        this.name = name;
        this.price = price;
    }
}