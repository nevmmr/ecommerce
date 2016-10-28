package com.ecommerce.infrastructure.repositories;

import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;
import com.ecommerce.domain.entity.Purchase;
import com.ecommerce.domain.repositories.PurchaseRepository;

@DomainRepositoryImpl
public class JpaPurchaseRepository extends GenericJpaRepository<Purchase> implements PurchaseRepository {


}
