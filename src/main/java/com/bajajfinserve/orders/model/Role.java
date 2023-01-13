package com.bajajfinserve.orders.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@ToString(exclude = "users")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String roleName;

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
	
	@ManyToMany
	@JoinTable(
			name="user_roles",
			joinColumns = @JoinColumn(name="role_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name="user_id", nullable = false)
			)
	private List<User> users = new ArrayList<>();
}
