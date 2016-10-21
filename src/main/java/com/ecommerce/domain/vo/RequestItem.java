package com.ecommerce.domain.vo;

import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.annotations.domain.ValueObject;

@ValueObject
public class RequestItem {

	private ProductData productData;
	
	private int quantity;
	
	private Money totalCost;
	
	public RequestItem(ProductData productData, int quantity, Money totalCost) {
		this.productData = productData;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public ProductData getProductData() {
		return productData;
	}

	public int getQuantity() {
		return quantity;
	}

}
