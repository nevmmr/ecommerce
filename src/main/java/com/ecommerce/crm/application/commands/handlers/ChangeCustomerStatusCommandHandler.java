package com.ecommerce.crm.application.commands.handlers;

import javax.inject.Inject;

import com.ecommerce.cqrs.annotations.CommandHandlerAnnotation;
import com.ecommerce.cqrs.interfaces.ICommandHandler;
import com.ecommerce.crm.application.commands.ChangeCustomerStatusCommand;
import com.ecommerce.crm.domain.Customer;
import com.ecommerce.crm.domain.CustomerRepository;

@CommandHandlerAnnotation
public class ChangeCustomerStatusCommandHandler implements ICommandHandler<ChangeCustomerStatusCommand, Void>{

	@Inject
	private CustomerRepository customerRepository; 
	
	@Override
	public Void handle(ChangeCustomerStatusCommand command) {
		Customer customer = customerRepository.load(command.getCustomerId());
		customer.changeStatus(command.getStatus());
		customerRepository.save(customer);		
		return null;
	}

}
