package com.ecommerce.application.listeners;

import javax.inject.Inject;

import com.ecommerce.application.services.DiscountingService;
import com.ecommerce.canonical.events.CustomerStatusChangedEvent;
import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.cqrs.PaginatedResult;
import com.ecommerce.ddd.annotations.events.EventListener;
import com.ecommerce.ddd.annotations.events.EventListeners;
import com.ecommerce.domain.dto.OrderDto;
import com.ecommerce.readmodel.orders.OrderFinder;
import com.ecommerce.readmodel.orders.OrderQuery;

@EventListeners
public class ClientStatusChangedListener {

	@Inject
	private DiscountingService discountingService;
	@Inject
	private OrderFinder orderFinder;
	
	@EventListener
	public void handle(CustomerStatusChangedEvent event){
		OrderQuery orderQuery = new OrderQuery(null, event.getCustomerId());
		PaginatedResult<OrderDto> orders = orderFinder.query(orderQuery);
		
		Money discount = calculateDiscout(event.getCustomerId());
		
		for(OrderDto dto : orders.getItems()){
			discountingService.applyDiscount(dto.getOrderId(), discount);
		}
	}

	private Money calculateDiscout(AggregateId customerId) {
		return new Money(10);
	}
}
