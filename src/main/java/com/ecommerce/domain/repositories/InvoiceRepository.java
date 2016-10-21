package com.ecommerce.domain.repositories;

import com.ecommerce.ddd.annotations.domain.DomainRepository;
import com.ecommerce.domain.entity.Invoice;


@DomainRepository
public interface InvoiceRepository {

	/**
	 * @param invoice
	 */
	public void save(Invoice invoice);

}
