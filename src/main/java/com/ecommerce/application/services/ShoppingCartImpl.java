package com.ecommerce.application.services;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.app.InternalApplicationService;

@InternalApplicationService
public class ShoppingCartImpl implements IShoppingCart{

	@Override
	public void add(AggregateId productId){

	}

}
