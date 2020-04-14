package com.classpath.ordermgmt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "orderId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private long orderId;

    @Column(name = "order_date")
    @JsonProperty("order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
    private LocalDate orderDate;

    @Column(name="merchant_name")
    @JsonProperty("merchant_name")
    private String merchantName;

    @OneToMany(mappedBy = "order",
            fetch = LAZY,
            cascade = {PERSIST, REMOVE})
    @JsonProperty("line_items")
    @Column(name = "line_items")
    private Set<OrderLineItem> orderLineItems;

    // scaffolding code
    public void addOrderLineItem(OrderLineItem orderLineItem){
        this.orderLineItems.add(orderLineItem);
        orderLineItem.setOrder(this);
    }
}