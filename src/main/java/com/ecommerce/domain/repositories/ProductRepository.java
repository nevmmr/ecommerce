package com.ecommerce.domain.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.domain.entity.Product;

@Repository
public interface ProductRepository {

	public Product load(AggregateId productId);
	
	public List<Product> findProductWhereBestBeforeExpiredIn(int days);
}
