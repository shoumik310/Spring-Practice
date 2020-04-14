package com.classpath.ordermgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "orderId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long orderId;

    private double price;

    @OneToMany(mappedBy = "order",
            fetch = LAZY,
            cascade = {PERSIST, REMOVE} )
    private Set<OrderLineItem> orderLineItems = new HashSet<>();

    // scaffolding code
    public void addOrderLineItem(OrderLineItem orderLineItem){
        this.orderLineItems.add(orderLineItem);
        orderLineItem.setOrder(this);
    }
}