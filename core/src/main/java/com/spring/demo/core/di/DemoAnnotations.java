package com.spring.demo.core.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoAnnotations {

	/**
	 *  This will automatically put all beans of type Priority in this list
	 *  Is no @Order Annotation used inserted in random order
	 *  This "Random" order might be alphabetical order
	 */
	@Autowired
	private List<Priority> priorities;
	
	/**
	 * Test to see if it picks up function beans
	 * Gives priority to 'Integer' beans over 'List<Integer>' beans
	 */
	@Autowired
	private List<Integer> ints;

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

			System.out.println("========== FIELD INJECTION DEMO ==========");
			UserAutowired adminUser3 = context.getBean("userAutowired", UserAutowired.class);
			System.out.println(adminUser3.getRole().getRoleName());

			System.out.println("========== QUALIFIER DEMO ==========");
			UserMultiRole adminUser4 = context.getBean("userMultiRole", UserMultiRole.class);
			System.out.println(adminUser4.getRole().getClass());

			System.out.println("========== INJECT COLLECTION DEMO ==========");
			UserWList user4 = context.getBean("userWList", UserWList.class);
			System.out.println(user4.getOrderIds());

			UserWList user5 = context.getBean("userWithList", UserWList.class);
			System.out.println(user5.getOrderIds());

			System.out.println("========== ORDERED COLLECTION DEMO ==========");
			DemoAnnotations demo = context.getBean("demoAnnotations", DemoAnnotations.class);
			for (Priority priority : demo.priorities) {
				System.out.println(priority.getPriorityRank());
			}
			
			System.out.println("+++++ AutoWiring Test +++++");
			for(Integer i: demo.ints) {
				System.out.println(i);
			}
			
			System.out.println("========== LOOKUP DEMO ==========");
			UserMessenger userMessenger = context.getBean("userMessenger", UserMessenger.class);
			System.out.println("Compare two messages: " + (userMessenger.getMessage() == userMessenger.getMessage()));
			System.out.println(userMessenger.getMessage());

		}

	}

}
