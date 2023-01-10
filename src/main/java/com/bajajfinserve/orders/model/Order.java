package com.bajajfinserve.orders.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private double price;
	
	@OneToMany(mappedBy = "order", cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<LineItem> lineItems;
	
	//scaffolding code
	public void addLineItem(LineItem lineItem) {
		if(this.lineItems == null) {
			this.lineItems = new ArrayList<LineItem>();
		}
		this.lineItems.add(lineItem);
		lineItem.setOrder(this);
		
	}

}
