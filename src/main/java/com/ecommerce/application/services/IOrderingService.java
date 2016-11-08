package com.ecommerce.application.services;

import com.ecommerce.application.commands.ShowOrderDetailsCommand;
import com.ecommerce.application.offer.OfferChangedException;
import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.domain.vo.Offer;

public interface IOrderingService {

	public AggregateId createOrder();

	public void addProduct(AggregateId orderId, AggregateId productId, int quantity);

	public void confirm(AggregateId orderId, ShowOrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedException;
}
