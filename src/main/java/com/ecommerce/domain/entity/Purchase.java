package com.ecommerce.domain.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ecommerce.canonical.events.OrderSubmittedEvent;
import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;

@Entity
@AggregateRoot
public class Purchase extends BaseAggregateRoot{

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@OrderColumn(name = "itemNumber")
	@JoinColumn(name = "purchase_id")
	private List<PurchaseItem> items;
	
	private boolean paid;

	@Embedded
	private CustomerData clientData;

	private Date purchaseDate;

	@Embedded
	private Money totalCost;

	
	@SuppressWarnings("unused")
	protected  Purchase() {}

	public Purchase(AggregateId aggregateId, CustomerData clientData, List<PurchaseItem> items, Date purchaseDate,
			boolean paid, Money totalCost){
		this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.items = items;
		this.purchaseDate = purchaseDate;
		this.paid = paid;
		this.totalCost = totalCost;
	}
	
	public void confirm() {
		paid = true;
		eventPublisher.publish(new OrderSubmittedEvent(getAggregateId()));
	}
	
	public boolean isPaid() {
		return paid;
	}
	
	public Money getTotalCost() {
		return totalCost;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public CustomerData getCustomerData() {
		return clientData;
	}
	
	public Collection<PurchaseItem> getItems() {
		return (Collection<PurchaseItem>) Collections.unmodifiableCollection(items);
	}
	
}
