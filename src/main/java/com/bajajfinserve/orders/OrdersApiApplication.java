package com.bajajfinserve.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				description = "API's for Order API",
				contact = @Contact(
						email = "developer@example.com",
						name = "Product-Owner",
						url = "http://myawesomeapp.com"
						),
				termsOfService = "http://myawesomeapp.com/terms.html"
				) )
public class OrdersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApiApplication.class, args);
	}

}
