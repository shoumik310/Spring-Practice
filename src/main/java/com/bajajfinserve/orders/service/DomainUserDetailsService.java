package com.bajajfinserve.orders.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bajajfinserve.orders.dao.UserJpaRepository;
import com.bajajfinserve.orders.model.DomainUserDetails;
import com.bajajfinserve.orders.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
	
	private final UserJpaRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User domainUser = this.userRepository
							.findByName(username).orElseThrow(() -> new UsernameNotFoundException("bad credentials"));
		return new DomainUserDetails(domainUser);
	}

}
