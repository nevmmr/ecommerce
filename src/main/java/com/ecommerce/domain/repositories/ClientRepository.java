package com.ecommerce.domain.repositories;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.domain.DomainRepository;
import com.ecommerce.domain.entity.Client;

@DomainRepository
public interface ClientRepository {

	public Client load(AggregateId id);

	public void save(Client client);
}
