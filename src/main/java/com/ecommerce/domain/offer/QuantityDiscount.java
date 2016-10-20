package com.ecommerce.domain.offer;

import com.ecommerce.canonical.vo.Money;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.domain.offer.DiscountPolicy;
import com.ecommerce.domain.vo.Discount;

public class QuantityDiscount implements DiscountPolicy{
	private double rebateRatio;
	
	private int mininalQuantity;
	
	/**
	 * 
	 * @param rebate value of the rebate in % 
	 * @param mininalQuantity minimal quantity of the purchase that allows rebate
	 */
	public QuantityDiscount(double rebate, int mininalQuantity) {
		rebateRatio = rebate / 100;
		this.mininalQuantity = mininalQuantity;
	}

	@Override
	public Discount applyDiscount(Product product, int quantity, Money regularCost) {
		if (quantity >= mininalQuantity)
			return new Discount("over: " + quantity, regularCost.multiplyBy(rebateRatio));
		return null;
	}
}
