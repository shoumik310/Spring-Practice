package com.bajajfinserve.orders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.bajajfinserve.orders.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final DomainUserDetailsService domainUserDetailsService;
	
	//configuring Authentication
	public void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
		authenticationManager
			.userDetailsService(this.domainUserDetailsService)
			.passwordEncoder(null);
		
	}
	
	
	
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.cors().disable(); http.csrf().disable();
	 * http.headers().frameOptions().disable(); http.authorizeHttpRequests()
	 * .antMatchers("/**").permitAll() .and() .httpBasic() .and() .formLogin();
	 * 
	 * }
	 */

	
}
