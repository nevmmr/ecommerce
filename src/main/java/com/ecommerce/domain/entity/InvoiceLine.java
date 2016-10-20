package com.ecommerce.domain.entity;

import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseEntity;
import com.ecommerce.domain.vo.ProductData;

import javax.persistence.*;


@Entity
public class InvoiceLine extends BaseEntity{
		
	@Embedded
	private ProductData product;
	
	private int quantity;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "net_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "net_currencyCode")) })
	private Money net;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "denomination", column = @Column(name = "gros_denomination")),
		@AttributeOverride(name = "currencyCode", column = @Column(name = "gros_currencyCode")) })
	private Money gros;

	public InvoiceLine(){}
	

	public InvoiceLine(ProductData product, int quantity, Money net) {
		this.product = product;
		this.quantity = quantity;
		this.net = net;
		
		this.gros = net;	
	}

	public ProductData getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public Money getNet() {
		return net;
	}

	public Money getGros() {
		return gros;
	}
	
}
