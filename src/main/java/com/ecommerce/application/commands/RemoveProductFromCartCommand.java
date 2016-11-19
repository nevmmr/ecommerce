package com.ecommerce.application.commands;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.cqrs.annotations.Command;

@Command()
public class RemoveProductFromCartCommand {

	private AggregateId orderId;
	private AggregateId productId;
	private int quantity;
	
	public RemoveProductFromCartCommand(AggregateId orderId, AggregateId productId,
			int quantity) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}
	
	public AggregateId getProductId() {
		return productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
