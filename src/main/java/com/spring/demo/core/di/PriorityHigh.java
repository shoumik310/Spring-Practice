package com.spring.demo.core.di;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PriorityHigh implements Priority {
	@Override
	public int getPriorityRank() {
		return 1;
	}
}
