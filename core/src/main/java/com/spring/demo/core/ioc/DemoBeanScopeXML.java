package com.spring.demo.core.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoBeanScopeXML {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user1 = context.getBean("userBean",User.class);
		User user2 = context.getBean("userBean",User.class);
		
		System.out.println("user1 == user2: " + (user1 == user2));
		
		User user3 = context.getBean("userPrototype",User.class);
		User user4 = context.getBean("userPrototype",User.class);
		
		System.out.println("user3 == user4: " + (user3 == user4));
		
		
	}
	

}
