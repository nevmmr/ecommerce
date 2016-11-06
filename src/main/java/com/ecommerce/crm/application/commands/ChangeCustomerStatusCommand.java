package com.ecommerce.crm.application.commands;

import java.io.Serializable;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.cqrs.annotations.Command;
import com.ecommerce.crm.domain.Customer.CustomerStatus;

@SuppressWarnings("serial")
@Command
public class ChangeCustomerStatusCommand implements Serializable{

	private AggregateId customerId;
	
	private CustomerStatus status;

	public ChangeCustomerStatusCommand(AggregateId customerId, CustomerStatus status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}

	public AggregateId getCustomerId() {
		return customerId;
	}

	public CustomerStatus getStatus() {
		return status;
	}
	
	
}
