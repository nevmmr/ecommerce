package com.ecommerce.canonical.events;

import java.io.Serializable;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.events.Event;


@SuppressWarnings("serial")
@Event
public class PaymentRolledBackEvent implements Serializable{

	private AggregateId paymentId;
	
	public PaymentRolledBackEvent(AggregateId paymentId){
		this.paymentId = paymentId;
	}
	
	public AggregateId getPaymentId() {
		return paymentId;
	}

}
