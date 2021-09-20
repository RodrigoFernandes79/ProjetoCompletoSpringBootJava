package com.projectdata.apirestdata.model.enums;

public enum OrderStatus{
		
	WAITING_PAYMENT ("Waiting Payment"),
	PAID ("Paid"),
	SHIPPED("Shipped"),
	DELIVERED("Delivered"),
	CANCELED ("Cancelled");

	

	private final String description;

	private OrderStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
