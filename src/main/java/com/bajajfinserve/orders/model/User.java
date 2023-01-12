package com.bajajfinserve.orders.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String password;
	private double salary;
	@ManyToMany(
			mappedBy = "users", 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Role> roles;
}
