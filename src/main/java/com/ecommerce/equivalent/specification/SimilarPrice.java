package com.ecommerce.equivalent.specification;

import com.ecommerce.canonical.specification.CompositeSpecification;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.domain.entity.Product;

public class SimilarPrice extends CompositeSpecification<Product>{

	private Money min;
	private Money max;
	
	public SimilarPrice(Money price, Money acceptableDifference) {
		this.min = price.subtract(acceptableDifference);
		this.max = price.add(acceptableDifference);
	}

	@Override
	public boolean isSatisfiedBy(Product candidate) {		
		return candidate.getPrice().greaterThan(min) && candidate.getPrice().lessThan(max);
	}

}
