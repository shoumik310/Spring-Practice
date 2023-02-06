package com.spring.demo.core.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoXML {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("========== CONSTRUCTOR INJECTION DEMO ==========");
		User adminUser1 = context.getBean("adminUser", User.class);
		User adminUser2 = context.getBean("adminUser2", User.class);
		User adminUser3 = context.getBean("adminUser3", User.class);
		System.out.println(adminUser1.getRole().getRoleName());
		System.out.println(adminUser2.getRole().getRoleName());
		System.out.println(adminUser3.getRole().getRoleName());	
		
		System.out.println("========== SETTER INJECTION DEMO ==========");
		User adminUser4 = context.getBean("adminUser4", User.class);
		System.out.println(adminUser4.getRole().getRoleName());
		
		System.out.println("========== AUTOWIRE BY TYPE DEMO ==========");
		User adminUser5 = context.getBean("adminUser5", User.class);
		System.out.println(adminUser5.getRole().getRoleName());
		
		System.out.println("========== COLLECTIONS INJECTION DEMO ==========");
		JavaCollectionInjection jc = (JavaCollectionInjection) context.getBean("javaCollection", JavaCollectionInjection.class);

		jc.getAddressList();
		jc.getAddressSet();
		jc.getAddressMap();
		jc.getAddressProperties();
	}
}
