package com.ecommerce.domain.repositories;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.domain.DomainRepository;
import com.ecommerce.domain.entity.Purchase;

@DomainRepository
public interface PurchaseRepository {

	Purchase load(AggregateId orderId);

	void save(Purchase purchase);
}
