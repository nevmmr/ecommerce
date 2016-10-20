package com.ecommerce.domain.entity;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;
import com.ecommerce.domain.productscatalog.ProductType;
import com.ecommerce.domain.vo.ProductData;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
@AggregateRoot
public class Product extends BaseAggregateRoot{

	@Embedded
	private Money price;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	
	@SuppressWarnings("unused")
	protected Product(){}
	
	Product(AggregateId aggregateId, Money price, String name, ProductType productType){
		this.aggregateId = aggregateId;
		this.price = price;
		this.name = name;
		this.productType = productType;
	}
	
	public boolean isAvailabe(){		
		return ! isRemoved();
	}
	
	public Money getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public ProductType getProductType() {
		return productType;
	}
	
	public ProductData generateSnapshot(){
		return new ProductData(getAggregateId(), price, name, productType, new Date());
	}
	
}
