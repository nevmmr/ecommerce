package com.ecommerce.canonical.events;

import java.io.Serializable;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.ddd.annotations.events.Event;

@SuppressWarnings("serial")
@Event
public class CustomerPaidEvent implements Serializable {

    private final AggregateId paymentId;
    private CustomerData userData;
    private Money amount;
    
    
    public CustomerPaidEvent(AggregateId paymentId, CustomerData clientData, Money amount) {
        this.paymentId = paymentId;
        this.userData = clientData;
        this.amount = amount;
    }

	public AggregateId getPaymentId() {
		return paymentId;
	}
	
	public CustomerData getClientData() {
		return userData;
	}
	
	public Money getAmount() {
		return amount;
	}
}
