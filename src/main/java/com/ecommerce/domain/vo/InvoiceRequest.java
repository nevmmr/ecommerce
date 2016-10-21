package com.ecommerce.domain.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.ddd.annotations.domain.ValueObject;

@ValueObject
public class InvoiceRequest {

	private CustomerData client;	
	private List<RequestItem> items = new ArrayList<RequestItem>();
	
	public InvoiceRequest(CustomerData client){
		this.client = client;
	}
	
	public void add(RequestItem item){
		items.add(item);
	}
	
	public CustomerData getClient() {
		return client;
	}
	
	public Collection<RequestItem> getItems() {
		return Collections.unmodifiableCollection(items);
	}

	public CustomerData getClientData() {
		return client;
	}
}
