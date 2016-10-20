package com.ecommerce.domain.entity;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AggregateRoot
@Entity
public class Invoice extends BaseAggregateRoot {

	@Embedded
	private CustomerData client;

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "invoiceId")
	@Fetch(FetchMode.JOIN)
	private List<InvoiceLine> items;

	public Invoice(AggregateId invoiceId, CustomerData client) {
		this.aggregateId = invoiceId;
		this.client = client;
		this.items = new ArrayList<InvoiceLine>();
		
		this.net = Money.ZERO;
		this.gros = Money.ZERO;
	}

	@SuppressWarnings("unused")
	protected Invoice(){}

	public void addItem(InvoiceLine item) {
		items.add(item);

		net = net.add(item.getNet());
		gros = gros.add(item.getGros());
	}

	public List<InvoiceLine> getItems() {
		return Collections.unmodifiableList(items);
	}

	public CustomerData getClient() {
		return client;
	}

	public Money getNet() {
		return net;
	}

	public Money getGros() {
		return gros;
	}

}
