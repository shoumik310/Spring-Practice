package com.bajajfinserve.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

	// private final DomainUserDetailsService domainUserDetailsService;

	/*
	 * // configuring Authentication public void
	 * configure(AuthenticationManagerBuilder authenticationManager) throws
	 * Exception {
	 * authenticationManager.userDetailsService(this.domainUserDetailsService).
	 * passwordEncoder(passwordEncoder());
	 * 
	 * }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeHttpRequests().antMatchers("/h2-console**", "/login**", "/logout**", "/contact-us**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/orders**").hasAnyRole("Everyone", "super_admins", "admins")
				.antMatchers(HttpMethod.POST, "/api/v1/orders**").hasAnyRole("super_admins", "admins")
				.antMatchers(HttpMethod.DELETE, "/api/v1/orders/**").hasAnyRole("super_admins").and()
				.oauth2ResourceServer().jwt();

	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("groups");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

}
