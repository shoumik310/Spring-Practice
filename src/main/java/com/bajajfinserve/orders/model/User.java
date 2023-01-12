package com.bajajfinserve.orders.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String password;
	private double salary;
	@ManyToMany(
			mappedBy = "users", 
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	
	public void addRole(Role role) {
		if(ObjectUtils.allNull(roles)) {
			this.roles = new HashSet<>();
		}
		this.roles.add(role);
		role.getUsers().add(this);
	}
}
