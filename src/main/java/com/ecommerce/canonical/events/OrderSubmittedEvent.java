package com.ecommerce.canonical.events;

import java.io.Serializable;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.events.Event;


@SuppressWarnings("serial")
@Event
public class OrderSubmittedEvent implements Serializable{

	private AggregateId orderId;
	
	public OrderSubmittedEvent(AggregateId orderId){
		this.orderId = orderId;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}

}
