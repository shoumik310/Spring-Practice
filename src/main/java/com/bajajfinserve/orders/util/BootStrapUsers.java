package com.bajajfinserve.orders.util;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.bajajfinserve.orders.dao.UserJpaRepository;
import com.bajajfinserve.orders.model.Role;
import com.bajajfinserve.orders.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootStrapUsers {
	
	private final UserJpaRepository userRepository;
	
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void onApplicationStartup(ApplicationReadyEvent event) {
		
		User kiran = User.builder().name("kiran").password("welcome").salary(4500000).build();
		
		User vinay = User.builder().name("vinay").password("welcome").salary(5500000).build();
		
		Role userRole = Role.builder().roleName("ROLE_USER").build();
		
		Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();
		
		kiran.addRole(userRole);
		
		vinay.addRole(userRole);
		vinay.addRole(adminRole);
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
		
	}

}
