package com.ecommerce.readmodel.orders;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.cqrs.PaginatedResult;
import com.ecommerce.ddd.annotations.app.Finder;
import com.ecommerce.domain.dto.OrderDto;

@Finder
public interface OrderFinder {

	OrderDto find(AggregateId orderId);

	PaginatedResult<OrderDto> query(OrderQuery orderQuery);
}
