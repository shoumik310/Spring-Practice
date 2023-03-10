package com.kimuohs.buyit.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

	@ManyToOne(fetch = FetchType.LAZY) // Since Many of "Product" type can go to one "Category"
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category; // Since it is many to one, only one object is needed

	private double price;
	private double weight;
	private String description;
	private String imageName;
}
