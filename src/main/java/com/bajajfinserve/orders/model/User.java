package com.bajajfinserve.orders.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@ToString(exclude = "roles")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String password;
	private double salary;
	
	public User(String name, String password, double salary) {
		super();
		this.name = name;
		this.password = password;
		this.salary = salary;
	}
	
	
	@ManyToMany(
			mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Role> roles = new ArrayList<>();
	
	
	public void addRole(Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}
}
