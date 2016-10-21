package com.ecommerce.domain.vo;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.annotations.domain.ValueObject;

@ValueObject
public class ReservedProduct {

	private String name;
	
	private Money totalCost;
	
	private AggregateId productId;

	private int quantity;
	
	public ReservedProduct(AggregateId productId, String name, int quantity, Money totalCost) {
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public String getName() {
		return name;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public AggregateId getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
