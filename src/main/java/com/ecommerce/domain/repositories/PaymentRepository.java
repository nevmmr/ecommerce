package com.ecommerce.domain.repositories;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.domain.DomainRepository;
import com.ecommerce.domain.entity.Payment;


@DomainRepository
public interface PaymentRepository {

	public Payment load(AggregateId paymentId);
	
	public void save(Payment payment);
}
