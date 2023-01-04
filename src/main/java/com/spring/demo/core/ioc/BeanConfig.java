package com.spring.demo.core.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Method Level Bean Config
 * @author shoumikpanandikar
 *
 */

@Configuration
public class BeanConfig {
	
	@Bean
	public UserAnnotation userAnnotation() {
		UserAnnotation user = new UserAnnotation();
		user.setFirstName("Bineet");
		user.setLastName("Chadha");
		return user;
	}
	
	@Bean(name = "customUser")
	public UserAnnotation userAnnotation2(){
		UserAnnotation user = new UserAnnotation();
		user.setFirstName("Vibhor");
		user.setLastName("Bhargava");
		return user;
	}
}
