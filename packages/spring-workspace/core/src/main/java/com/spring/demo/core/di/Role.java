package com.spring.demo.core.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Role {

	@Value("ADMIN_ROLE")
	private String roleName;

	public Role() {
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
