package com.spring.demo.core.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoBeanScopeAnnotation {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.demo.core.ioc");
		context.refresh();
		
		UserAnnotation user1 = context.getBean("user",UserAnnotation.class);
		UserAnnotation user2 = context.getBean("user",UserAnnotation.class);
		
		System.out.println("user1 == user2: " + (user1 == user2));
		
		UserProto user3 = context.getBean("userPrototype",UserProto.class);
		UserProto user4 = context.getBean("userPrototype",UserProto.class);
		
		System.out.println("user3 == user4: " + (user3 == user4));
		
		
	}
	

}
