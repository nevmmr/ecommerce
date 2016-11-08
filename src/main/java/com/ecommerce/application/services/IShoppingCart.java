package com.ecommerce.application.services;

import com.ecommerce.canonical.models.AggregateId;

public interface IShoppingCart {
	public void add(AggregateId productId);
}
