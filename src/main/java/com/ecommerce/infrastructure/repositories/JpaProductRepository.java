package com.ecommerce.infrastructure.repositories;

import java.util.List;

import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.domain.repositories.ProductRepository;

@DomainRepositoryImpl
public class JpaProductRepository extends GenericJpaRepository<Product> implements ProductRepository{

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductWhereBestBeforeExpiredIn(int days) {
		return entityManager.createQuery("FROM Product p").getResultList();
	}

}
