package com.ecommerce.crm.domain;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.domain.DomainRepository;

@DomainRepository
public interface CustomerRepository {

	public Customer load(AggregateId id);

	public void save(Customer entity);
}
