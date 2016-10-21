package com.ecommerce.domain.services;

import java.util.List;

import javax.inject.Inject;

import com.ecommerce.canonical.specification.Specification;
import com.ecommerce.ddd.annotations.domain.DomainService;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.domain.factories.ProductSpecificationFactory;
import com.ecommerce.domain.repositories.ProductRepository;
import com.ecommerce.domain.vo.Offer;

@DomainService
public class SuggestionService {

	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private Offer offer;
	
	@Inject
	private ProductSpecificationFactory productSpecificationFactory;
	
	public Product suggestEquivalent(Product problematicProduct, Client client) {
		List<Product> expiringProducts = productRepository.findProductWhereBestBeforeExpiredIn(5);
		
		Specification<Product> specification = productSpecificationFactory.create(client, problematicProduct);
		
		for (Product suggestedProduct : expiringProducts) {
			if (specification.isSatisfiedBy(suggestedProduct))
				return suggestedProduct;
		}
		
		return null;
	}

}
