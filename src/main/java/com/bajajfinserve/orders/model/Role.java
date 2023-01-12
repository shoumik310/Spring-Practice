package com.bajajfinserve.orders.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String roleName;
	@ManyToMany
	@JoinTable(
			name="user_roles",
			joinColumns = @JoinColumn(name="role_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name="user_id", nullable = false)
			)
	private Set<User> users = new HashSet<>();
	
	public Set<User> getUsers(){
		if(this.users == null) {
			return new HashSet<>();
		}
		return this.users;
	}

}
