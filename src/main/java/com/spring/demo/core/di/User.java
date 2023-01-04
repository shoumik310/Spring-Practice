package com.spring.demo.core.di;

public class User {

	private Role role;

	public User() {
	}

	public User(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
