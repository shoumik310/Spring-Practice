package com.spring.demo.core.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAutowired {

	//Field Injection
	@Autowired
	private Role role;

	public Role getRole() {
		return role;
	}

}
