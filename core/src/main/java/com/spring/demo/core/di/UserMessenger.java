package com.spring.demo.core.di;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class UserMessenger {

	private Message message;
	
	// Makes sure that every call has a new prototyoe
	@Lookup
	public Message getMessage() {
		// This method body will be overridden by the @Lookup
		return null;
	}
	
	
}
