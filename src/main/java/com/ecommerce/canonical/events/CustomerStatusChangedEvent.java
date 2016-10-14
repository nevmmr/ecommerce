package com.ecommerce.canonical.events;

import java.io.Serializable;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.crm.domain.Customer.CustomerStatus;
import com.ecommerce.ddd.annotations.events.Event;

@SuppressWarnings("serial")
@Event
public class CustomerStatusChangedEvent implements Serializable {

    private final AggregateId customerId;
    private final CustomerStatus status;

    public CustomerStatusChangedEvent(AggregateId customerId, CustomerStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public AggregateId getCustomerId() {
        return customerId;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}
