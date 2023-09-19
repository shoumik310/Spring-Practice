package com.spring.demo.core.ioc;

import org.springframework.stereotype.*;
/**
 * Demo of class level annotation
 * 
 * @author shoumikpanandikar
 * 
 */
@Component("user")
//@Controller
//@Service
//@Repository
public class UserAnnotation {

	private String firstName;
	private String lastName;
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserAnnotation [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
