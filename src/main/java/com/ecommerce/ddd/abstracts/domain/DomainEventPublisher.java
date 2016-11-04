package com.ecommerce.ddd.abstracts.domain;

import java.io.Serializable;

public interface DomainEventPublisher {
    void publish(Serializable event);
}
