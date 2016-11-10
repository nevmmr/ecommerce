package com.ecommerce.domain.factories;


import com.ecommerce.canonical.specification.DisjunctionSpecification;
import com.ecommerce.canonical.specification.Specification;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.equivalent.specification.SameCategory;
import com.ecommerce.equivalent.specification.SimilarName;
import com.ecommerce.equivalent.specification.SimilarPrice;

@DomainFactory
public class ProductSpecificationFactory {

	@SuppressWarnings("unchecked")
	public Specification<Product> create(Client client,
			Product problematicProduct) {
		return new DisjunctionSpecification<Product>(
					new SimilarPrice(problematicProduct.getPrice(), generateAcceptableDifference(client)),
					new SimilarName(problematicProduct.getName()),
					new SameCategory(problematicProduct.getProductType()));
	}

	private Money generateAcceptableDifference(Client client) {
		return new Money(7);
	}

}
