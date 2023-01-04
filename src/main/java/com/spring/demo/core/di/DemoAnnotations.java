package com.spring.demo.core.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoAnnotations {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("com.spring.demo.core.di");
			context.refresh();

			System.out.println("========== CONSTRUCTOR INJECTION DEMO ==========");
			User adminUser = context.getBean("adminUser", User.class);
			System.out.println(adminUser.getRole().getRoleName());

			System.out.println("========== SETTER INJECTION DEMO ==========");
			User adminUser2 = context.getBean("adminUser2", User.class);
			System.out.println(adminUser2.getRole().getRoleName());
			
		}

	}

}
