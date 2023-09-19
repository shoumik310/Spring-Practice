package com.bajajfinserve.orders.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebApplicationConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		log.info("Configuring the CORS configuration ");
		registry.addMapping("/api/v1/orders**").allowedOrigins("*");
	}
	
	/*
	 * @Override public void
	 * configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	 * log.info("Inside the content negotioation " );
	 * configurer.defaultContentType(MediaType.APPLICATION_XML)
	 * .favorParameter(true) .parameterName("format")
	 * .ignoreUnknownPathExtensions(true); }
	 */

}