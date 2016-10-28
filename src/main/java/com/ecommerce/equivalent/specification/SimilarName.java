package com.ecommerce.equivalent.specification;

import com.ecommerce.canonical.specification.CompositeSpecification;
import com.ecommerce.domain.entity.Product;

public class SimilarName extends CompositeSpecification<Product>{

	private String name;
	
	public SimilarName(String name) {
		this.name = name;
	}

	@Override
	public boolean isSatisfiedBy(Product candidate) {		
		return candidate.getName().contains(name) || candidate.getProductType().toString().contains(name);
	}

}
