package com.spring.demo.core.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserWList {

	/**
	 * Auto wired by type by default -> Name does not matter
	 * If multiple of same type found -> Primary Bean
	 * If multiple found and no bean marked as primary -> By Name
	 * If multiple found and no bean found matching name of property -> Exception 
	 */
	@Autowired 
	private List<String> orderIds;

	public UserWList() {
	}

	public UserWList(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

}
