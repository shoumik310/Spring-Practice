package com.bajajfinserve.orders.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="line_items")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(exclude = "order")
public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int qty;
	private double price;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable = false)
	@JsonBackReference
	private Order order;
	
}
