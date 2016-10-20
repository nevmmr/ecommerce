package com.ecommerce.domain.offer;

import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.annotations.domain.DomainPolicy;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.domain.vo.Discount;

@DomainPolicy
public interface DiscountPolicy {

	public Discount applyDiscount(Product product, int quantity, Money reularCost);
}
