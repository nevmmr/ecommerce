package com.ecommerce.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ecommerce.common.exceptions.DomainOperationException;
import com.ecommerce.ddd.abstracts.domain.BaseEntity;

@Entity
class ReservationItem extends BaseEntity{

	@ManyToOne
	private Product product;
	
	private int quantity;

	@SuppressWarnings("unused")
	protected ReservationItem(){}
	
	ReservationItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	void changeQuantityBy(int change) {
		int changed = quantity + change;
		if (changed <= 0)
			throw new DomainOperationException(null, "change below 1");
		this.quantity = changed;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	
}
