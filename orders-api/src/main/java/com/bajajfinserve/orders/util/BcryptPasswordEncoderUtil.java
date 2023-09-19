package com.bajajfinserve.orders.util;

import java.util.stream.Stream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BcryptPasswordEncoderUtil {
	
	public static void main(String[] args) {
		String password = "welcome";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword1 = passwordEncoder.encode(password);
		String encodedPassword2 = passwordEncoder.encode(password);
		String encodedPassword3 = passwordEncoder.encode(password);
		String encodedPassword4 = passwordEncoder.encode(password);
		
		Stream.of(encodedPassword1, encodedPassword2, encodedPassword3, encodedPassword4)
				.forEach(System.out::println);
		
		System.out.println("Password matches :: "+ passwordEncoder.matches("welc.ome", encodedPassword1));
		System.out.println("Password matches :: "+ passwordEncoder.matches("welcome", encodedPassword2));
		System.out.println("Password matches :: "+ passwordEncoder.matches("welcome", encodedPassword3));
		System.out.println("Password matches :: "+ passwordEncoder.matches("welcome", encodedPassword4));
		
	}

}
