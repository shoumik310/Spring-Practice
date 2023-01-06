package com.spring.demo.core.di;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PriorityLow implements Priority {
	@Override
	public int getPriorityRank() {
		return 3;
	}
}
