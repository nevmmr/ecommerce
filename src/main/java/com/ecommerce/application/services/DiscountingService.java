package com.ecommerce.application.services;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.annotations.app.InternalApplicationService;

@InternalApplicationService
public class DiscountingService {

	public void applyDiscount(AggregateId orderId, Money amount){
	}
}
