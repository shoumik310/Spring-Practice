package com.spring.demo.core.di;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class PriorityMedium implements Priority {
	@Override
	public int getPriorityRank() {
		return 2;
	}
}
