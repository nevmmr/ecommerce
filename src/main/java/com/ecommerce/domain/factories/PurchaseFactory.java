package com.ecommerce.domain.factories;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.common.exceptions.DomainOperationException;
import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Purchase;
import com.ecommerce.domain.entity.PurchaseItem;
import com.ecommerce.domain.vo.Offer;
import com.ecommerce.domain.vo.OfferItem;

@DomainFactory
public class PurchaseFactory {

	@Inject
	private AutowireCapableBeanFactory spring;

	public Purchase create(AggregateId orderId, Client client, Offer offer){
		if (! canPurchse(client, offer.getAvailabeItems()))
			throw new DomainOperationException(client.getAggregateId(), "client can not purchase");
		
		ArrayList<PurchaseItem> items = new ArrayList<PurchaseItem>(offer.getAvailabeItems().size());
		Money purchaseTotlCost = Money.ZERO;
		
		for (OfferItem item : offer.getAvailabeItems()) {
			PurchaseItem purchaseItem = new PurchaseItem(item.getProductData(), item.getQuantity(), item.getTotalCost());
			items.add(purchaseItem);
			purchaseTotlCost = purchaseTotlCost.add(purchaseItem.getTotalCost());
		}
		
		Purchase purchase = new Purchase(orderId, client.generateSnapshot(),
				items, new Date(), false, purchaseTotlCost);
		
		spring.autowireBean(purchase);
		
		return purchase;
	}

	private boolean canPurchse(Client client, List<OfferItem> availabeItems) {
		return true;
	}
}
