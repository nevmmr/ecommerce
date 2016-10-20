package com.ecommerce.domain.entity;

import com.ecommerce.canonical.events.PaymentRolledBackEvent;
import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;
import com.ecommerce.domain.factories.PaymentFactory;

import javax.inject.Inject;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

@AggregateRoot
@Entity
public class Payment extends BaseAggregateRoot{

	@Embedded
	private CustomerData clientData;
	
	@Embedded
	private Money amount;
	
	@Transient
	@Inject
	private PaymentFactory paymentFactory;
	
	@SuppressWarnings("unused")
	protected Payment(){}
	
	public Payment(AggregateId aggregateId, CustomerData clientData, Money amount) {
		this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.amount = amount;
	}

	public Payment rollBack(){
		eventPublisher.publish(new PaymentRolledBackEvent(getAggregateId()));
		return paymentFactory.createPayment(clientData, amount.multiplyBy(-1));
	}
}
