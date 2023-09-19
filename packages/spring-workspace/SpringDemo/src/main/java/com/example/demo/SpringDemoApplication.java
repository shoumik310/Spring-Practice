package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringDemoApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Triangle triangle = (Triangle)context.getBean("triangle-alias");
		triangle.draw();
		
	}

}
