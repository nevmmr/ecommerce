package com.ecommerce.domain.dto;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.readmodel.orders.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

	private AggregateId orderId;
	private List<OrderedProductDto> orderedProducts = new ArrayList<OrderedProductDto>();
	private OrderStatus status;
	private Boolean confirmable;

	public AggregateId getOrderId() {
		return orderId;
	}

	public void setOrderId(AggregateId orderId) {
		this.orderId = orderId;
	}

	public List<OrderedProductDto> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProductDto> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Boolean getConfirmable() {
		return confirmable;
	}

	public void setConfirmable(Boolean confirmable) {
		this.confirmable = confirmable;
	}
}