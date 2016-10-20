package com.ecommerce.domain.entity;

import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;
import com.ecommerce.domain.factories.PaymentFactory;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@AggregateRoot
public class Client extends BaseAggregateRoot{

	private String name;
	
	@Inject
	@Transient
	private PaymentFactory paymentFactory;
	
	public CustomerData generateSnapshot(){
		return new CustomerData(aggregateId, name);
	}

	public boolean canAfford(Money amount) {		
		return true;
	}


	public Payment charge(Money amount) {
		if (! canAfford(amount)){			
			domainError("Can not afford: " + amount);
		}

		return paymentFactory.createPayment(generateSnapshot(), amount);
	}
}
