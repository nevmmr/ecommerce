package com.ecommerce.infrastructure.repositories;

import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;
import com.ecommerce.domain.entity.Invoice;
import com.ecommerce.domain.repositories.InvoiceRepository;

@DomainRepositoryImpl
public class JpaInvoiceRepository extends GenericJpaRepository<Invoice> implements InvoiceRepository{

}
