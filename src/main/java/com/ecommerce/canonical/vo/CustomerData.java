package com.ecommerce.canonical.vo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.domain.ValueObject;

/**
 * Client's snapshot
 */
@ValueObject
@Embeddable
public class CustomerData {
	
	@Embedded
	@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "clientId", nullable = false))})
	private AggregateId aggregateId;
	
	private String name;

	protected CustomerData(){}
	
	public CustomerData(AggregateId aggregateId, String name) {
		this.aggregateId = aggregateId;
		this.name = name;
	}
	
	public AggregateId getAggregateId() {
		return aggregateId;
	}
	
	public String getName() {
		return name;
	}

}
