package com.ecommerce.readmodel.orders;

import com.ecommerce.canonical.models.AggregateId;

public class OrderQuery {

	private String productName;
	
	public OrderQuery(String productName, AggregateId clientId){
		this.productName = productName;
		//TODO search by client
	}
	
	public String getProductName() {
		return productName;
	}
}
