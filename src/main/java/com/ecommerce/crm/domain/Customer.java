package com.ecommerce.crm.domain;

import com.ecommerce.canonical.events.CustomerStatusChangedEvent;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@AggregateRoot
public class Customer extends BaseAggregateRoot{

	public enum CustomerStatus{
		STANDARD, VIP, PLATINUM
	}
	
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	
	public void changeStatus(CustomerStatus status){
		if (status.equals(this.status))
			return;
		
		this.status = status;
		eventPublisher.publish(new CustomerStatusChangedEvent(getAggregateId(), status));
	}
}
