package com.ecommerce.ddd.abstracts.domain;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.common.exceptions.DomainOperationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.*;

@Component
@Scope("prototype")
@MappedSuperclass
public abstract class BaseAggregateRoot {
	public static enum AggregateStatus {
		ACTIVE, ARCHIVE
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "aggregateId", column = @Column(name = "aggregateId", nullable = false) ) })
	protected AggregateId aggregateId;

	@Version
	private Long version;

	@Enumerated(EnumType.ORDINAL)
	private AggregateStatus aggregateStatus = AggregateStatus.ACTIVE;

	@Transient
	@Inject
	protected DomainEventPublisher eventPublisher;

	public void markAsRemoved() {
		aggregateStatus = AggregateStatus.ARCHIVE;
	}

	public AggregateId getAggregateId() {
		return aggregateId;
	}

	public boolean isRemoved() {
		return aggregateStatus == AggregateStatus.ARCHIVE;
	}

	protected void domainError(String message) {
		throw new DomainOperationException(aggregateId, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof BaseAggregateRoot) {
			BaseAggregateRoot other = (BaseAggregateRoot) obj;
			if (other.aggregateId == null)
				return false;
			return other.aggregateId.equals(aggregateId);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return aggregateId.hashCode();
	}
}
