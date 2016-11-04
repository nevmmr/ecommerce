package com.ecommerce.domain.dto;

import com.ecommerce.canonical.models.AggregateId;

public class OrderedProductDto {
	private AggregateId offerId;

	public AggregateId getOfferId() {
		return offerId;
	}

	public void setOfferId(AggregateId offerId) {
		this.offerId = offerId;
	}
}