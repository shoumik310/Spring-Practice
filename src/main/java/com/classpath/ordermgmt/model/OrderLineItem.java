package com.classpath.ordermgmt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "order_line_item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;

    public OrderLineItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

}