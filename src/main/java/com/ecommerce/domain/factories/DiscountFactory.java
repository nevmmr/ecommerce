package com.ecommerce.domain.factories;

import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.offer.DiscountPolicy;
import com.ecommerce.domain.offer.QuantityDiscount;

@DomainFactory
public class DiscountFactory {

	public DiscountPolicy create(Client client) {
		return new QuantityDiscount(20, 3);
	}

}
