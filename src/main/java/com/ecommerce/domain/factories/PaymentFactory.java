package com.ecommerce.domain.factories;

import com.ecommerce.canonical.events.CustomerPaidEvent;
import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.DomainEventPublisher;
import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Payment;

import javax.inject.Inject;

@DomainFactory
public class PaymentFactory {
	
	@Inject
	private DomainEventPublisher publisher;

	public Payment createPayment(CustomerData clientData, Money amount){

		AggregateId aggregateId = AggregateId.generate();
		publisher.publish(new CustomerPaidEvent(aggregateId, clientData, amount));
		return new Payment(aggregateId, clientData, amount);
	}
}
