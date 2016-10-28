package com.ecommerce.equivalent.specification;



import com.ecommerce.canonical.specification.CompositeSpecification;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.domain.productscatalog.ProductType;

public class SameCategory extends CompositeSpecification<Product>{

	private ProductType productType;
	
	public SameCategory(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public boolean isSatisfiedBy(Product candidate) {
		return candidate.getProductType().equals(productType);
	}

}
