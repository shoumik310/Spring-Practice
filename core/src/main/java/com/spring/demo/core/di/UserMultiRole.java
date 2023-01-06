package com.spring.demo.core.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserMultiRole {

//	@Autowired(required = false)
	private RoleInterface role;

	public UserMultiRole() {
	}
	
//	@Autowired
	public UserMultiRole(RoleInterface role) {
		this.role = role;
	}

	public RoleInterface getRole() {
		return role;
	}

	@Autowired
	public void setRole(RoleInterface role) {
		this.role = role;
	}

//	@Autowired
//	public void setRole(@Qualifier("emplRole") RoleInterface role) {
//		this.role = role;
//	}

}
