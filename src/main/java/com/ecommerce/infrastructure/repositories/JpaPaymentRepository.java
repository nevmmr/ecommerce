package com.ecommerce.infrastructure.repositories;

import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;
import com.ecommerce.domain.entity.Payment;
import com.ecommerce.domain.repositories.PaymentRepository;

@DomainRepositoryImpl
public class JpaPaymentRepository extends GenericJpaRepository<Payment> implements PaymentRepository {

}
