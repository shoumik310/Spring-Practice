package com.bajajfinserve.orders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeHttpRequests()
		.antMatchers("/**").permitAll()
		.and()
		.httpBasic()
		.and()
		.formLogin();
		
	}

	
}
