package com.ecommerce.domain.repositories;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.annotations.domain.DomainRepository;
import com.ecommerce.domain.entity.Reservation;

@DomainRepository
public interface ReservationRepository {

	void save(Reservation reservation);

	Reservation load(AggregateId reservationId);
}
