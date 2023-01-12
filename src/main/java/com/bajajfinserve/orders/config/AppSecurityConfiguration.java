package com.bajajfinserve.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bajajfinserve.orders.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsService domainUserDetailsService;

	// configuring Authentication
	public void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
		authenticationManager.userDetailsService(this.domainUserDetailsService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http
			.authorizeHttpRequests()
			.antMatchers("/h2-console**", "/login**", "/logout**", "/contact-us**")
				.permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/orders**")
				.hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.POST, "/api/v1/orders**")
				.hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/v1/orders/**")
				.hasRole("ADMIN")
			.and()
			.httpBasic()
			.and()
			.formLogin();

	}

}
