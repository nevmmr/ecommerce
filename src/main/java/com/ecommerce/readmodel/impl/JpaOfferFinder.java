package com.ecommerce.readmodel.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecommerce.ddd.annotations.domain.FinderImpl;
import com.ecommerce.domain.dto.OfferedProductDto;
import com.ecommerce.readmodel.offer.Offer;
import com.ecommerce.readmodel.offer.OfferQuery;

@FinderImpl
public class JpaOfferFinder implements Offer {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<OfferedProductDto> find(OfferQuery query) {
		boolean bestBeforeExpired = query.isBestBeforeExpired();

		return (List<OfferedProductDto>) entityManager
				.createQuery("")
				.getResultList();
	}

}
