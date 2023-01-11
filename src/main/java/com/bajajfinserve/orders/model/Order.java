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
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

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
	@NotEmpty(message ="customer name cannot be blank")
	private String name;
	@NotEmpty(message = "email address cannot be empty")
	@Email(message = "email address is not in correct format")
	private String email;
	
	@PastOrPresent(message = "order date cannot be in future")
	private LocalDate orderDate;
	
	@Min(value = 500, message = "min order price should be 500")
	@Max(value = 10000, message = "max order price cannot be more than 4000")
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
