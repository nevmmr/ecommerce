package com.ecommerce.application.listeners;



import javax.inject.Inject;

import com.ecommerce.domain.services.BookKeeper;
import com.ecommerce.domain.vo.InvoiceRequest;
import com.ecommerce.canonical.events.OrderSubmittedEvent;
import com.ecommerce.ddd.annotations.events.EventListener;
import com.ecommerce.ddd.annotations.events.EventListeners;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Invoice;
import com.ecommerce.domain.entity.Purchase;
import com.ecommerce.domain.factories.InvoiceRequestFactory;
import com.ecommerce.domain.repositories.ClientRepository;
import com.ecommerce.domain.repositories.InvoiceRepository;
import com.ecommerce.domain.repositories.PurchaseRepository;

@EventListeners
public class BookKeepingListener {

	@Inject
	private BookKeeper bookKeeper;
	
	@Inject
	private PurchaseRepository purchaseRepository;
	
	@Inject
	private InvoiceRepository invoiceRepository;
		
	@Inject
	private ClientRepository clientRepository;
	
	@Inject
	private InvoiceRequestFactory invoiceRequestFactory;
	
	@EventListener
	public void handle(OrderSubmittedEvent event){
		Purchase purchase = purchaseRepository.load(event.getOrderId());
		
		Client client = clientRepository.load(purchase.getCustomerData().getAggregateId());
		InvoiceRequest request  = invoiceRequestFactory.create(client, purchase); 
		Invoice invoice = bookKeeper.issuance(request);
		
		invoiceRepository.save(invoice);
	}
}
