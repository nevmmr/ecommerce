package com.ecommerce.readmodel.impl;


import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.cqrs.PaginatedResult;
import com.ecommerce.ddd.annotations.domain.FinderImpl;
import com.ecommerce.domain.dto.OrderDto;
import com.ecommerce.domain.dto.OrderedProductDto;
import com.ecommerce.domain.entity.Purchase;
import com.ecommerce.domain.entity.Reservation;
import com.ecommerce.domain.vo.ReservedProduct;
import com.ecommerce.readmodel.orders.OrderFinder;
import com.ecommerce.readmodel.orders.OrderQuery;
import com.ecommerce.readmodel.orders.OrderStatus;
import com.google.common.base.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.transform;

@FinderImpl
public class JpaOrderFinder implements OrderFinder {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public OrderDto find(AggregateId orderId) {
		Reservation reservation = entityManager.find(Reservation.class, orderId);
		Purchase purchase = entityManager.find(Purchase.class, orderId);
		
		return toOrderDto(reservation, purchase);
	}

	private OrderDto toOrderDto(Reservation reservation, Purchase purchase) {
		OrderDto dto = new OrderDto();
		dto.setOrderId(reservation.getAggregateId());
		List<ReservedProduct> reservedProducts = reservation.getReservedProducts();
		dto.setOrderedProducts(new ArrayList<OrderedProductDto>(transform(reservedProducts,
				reservedProductToOrderedProductDto())));
		if (purchase != null) {
			dto.setStatus(OrderStatus.CONFIRMED);
			
		} else {
			dto.setStatus(OrderStatus.NEW);
		}
		return dto;
	}

	private static Function<ReservedProduct, OrderedProductDto> reservedProductToOrderedProductDto() {
		return new Function<ReservedProduct, OrderedProductDto>() {
			public OrderedProductDto apply(ReservedProduct product) {
				OrderedProductDto dto = new OrderedProductDto();
				dto.setOfferId(product.getProductId());
				return dto;
			}
		};
	}

	@Override
	public PaginatedResult<OrderDto> query(OrderQuery orderQuery) {
		return null;
	}

}
