package com.bajajfinserve.orders.util;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bajajfinserve.orders.dao.RoleJpaRepository;
import com.bajajfinserve.orders.dao.UserJpaRepository;
import com.bajajfinserve.orders.model.Role;
import com.bajajfinserve.orders.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootStrapUsers {
	
	private final UserJpaRepository userRepository;
	private final RoleJpaRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationStartup(ApplicationReadyEvent event) {
		
		User kiran = User.builder().name("kiran").password(this.passwordEncoder.encode("welcome")).salary(4500000).build();
		
		User vinay = User.builder().name("vinay").password(this.passwordEncoder.encode("welcome")).salary(5500000).build();
		
		Role userRole = Role.builder().roleName("ROLE_USER").build();
		
		Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();
		
		this.roleRepository.save(userRole);
		this.roleRepository.save(adminRole);
		
		kiran.addRole(userRole);
		
		vinay.addRole(userRole);
		vinay.addRole(adminRole);
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
		
	}

}
