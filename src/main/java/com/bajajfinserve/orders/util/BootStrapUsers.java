package com.bajajfinserve.orders.util;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void onApplicationStartup(ApplicationReadyEvent event) {
		
		User kiran = new User("kiran", this.passwordEncoder.encode("welcome"), 4500000);
		
		User vinay = new User("vinay", this.passwordEncoder.encode("welcome"), 4500000);		
		
		
		
		Role userRole = new Role("ROLE_USER");
		
		Role adminRole = new Role("ROLE_ADMIN");
		
		
		kiran.addRole(userRole);
		
		vinay.addRole(userRole);
		vinay.addRole(adminRole);
		
		this.userRepository.save(kiran);
		this.userRepository.save(vinay);
	
		
	}

}
