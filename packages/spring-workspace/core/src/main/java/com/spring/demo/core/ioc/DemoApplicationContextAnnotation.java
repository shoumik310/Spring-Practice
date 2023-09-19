package com.spring.demo.core.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoApplicationContextAnnotation {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.spring.demo.core.ioc");
		context.refresh();

		UserAnnotation user = context.getBean("user", UserAnnotation.class);

		System.out.println(user);

		UserAnnotation user2 = context.getBean("userAnnotation", UserAnnotation.class);
		UserAnnotation user3 = context.getBean("customUser", UserAnnotation.class);
		System.out.println(user2);
		System.out.println(user3);

		Product variantProduct = context.getBean("variantProduct", Product.class);
		Product masterProduct = context.getBean("masterProduct", Product.class);
		System.out.println(variantProduct.getClass().getName());
		System.out.println(masterProduct.getClass().getName());

		SpecialOffer specialOffer = context.getBean("specialOffer", SpecialOffer.class);
		System.out.println(
				"Master Product injected into Special Offer: " + specialOffer.masterProduct.getClass().getName());
		;
		System.out.println(
				"Variant Product injected into Special Offer: " + specialOffer.variantProduct.getClass().getName());
		;
	}

}
