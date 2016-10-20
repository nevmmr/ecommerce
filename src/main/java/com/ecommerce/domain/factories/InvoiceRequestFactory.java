package com.ecommerce.domain.factories;

import com.ecommerce.common.exceptions.DomainOperationException;
import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Purchase;
import com.ecommerce.domain.entity.PurchaseItem;
import com.ecommerce.domain.vo.InvoiceRequest;
import com.ecommerce.domain.vo.RequestItem;

@DomainFactory
public class InvoiceRequestFactory {

	public InvoiceRequest create(Client client, Purchase... purchases) {
		InvoiceRequest request = new InvoiceRequest(client.generateSnapshot());
		
		for (Purchase purchase : purchases) {
			if (! purchase.isPaid())
				throw new DomainOperationException(purchase.getAggregateId(), "Purchase is not paid");
			
			for (PurchaseItem item : purchase.getItems()) {
				request.add(new RequestItem(item.getProductData(), item.getQuantity(), item.getTotalCost()));
			}
		}
		
		return request;
	}

}
