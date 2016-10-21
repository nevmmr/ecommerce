package com.ecommerce.domain.services;

import javax.inject.Inject;

import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.annotations.domain.DomainService;
import com.ecommerce.domain.entity.Invoice;
import com.ecommerce.domain.entity.InvoiceLine;
import com.ecommerce.domain.factories.InvoiceFactory;
import com.ecommerce.domain.repositories.ProductRepository;
import com.ecommerce.domain.vo.InvoiceRequest;
import com.ecommerce.domain.vo.RequestItem;

@DomainService
public class BookKeeper {
	
	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private InvoiceFactory invoiceFactory;
	
	public Invoice issuance(InvoiceRequest invoiceRequest){
		Invoice invoice = invoiceFactory.create(invoiceRequest.getClientData());
		
		for (RequestItem item : invoiceRequest.getItems()){
			Money net = item.getTotalCost();			
				
						
			InvoiceLine invoiceLine = new InvoiceLine(item.getProductData(), item.getQuantity(), net);			
			invoice.addItem(invoiceLine);
		}
		
		return invoice;
	}
	
}
