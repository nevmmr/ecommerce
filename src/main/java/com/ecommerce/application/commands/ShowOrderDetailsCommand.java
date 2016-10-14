package com.ecommerce.application.commands;

import com.ecommerce.cqrs.annotations.Command;

@Command
public class ShowOrderDetailsCommand {
	public Long shippingAddressId;
	public Long deliveryOptionId;
	public Long paymentOptionId;
}
