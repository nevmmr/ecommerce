package com.ecommerce.domain.factories;



import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Invoice;


@DomainFactory
public class InvoiceFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Invoice create(CustomerData client){
		Invoice invoice = new Invoice(AggregateId.generate(), client);
		spring.autowireBean(invoice);
		return invoice;
	}
}
