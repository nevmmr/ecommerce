package com.ecommerce.infrastructure.repositories;

import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;
import com.ecommerce.domain.entity.Reservation;
import com.ecommerce.domain.repositories.ReservationRepository;

@DomainRepositoryImpl
public class JpaReservationRepository extends GenericJpaRepository<Reservation> implements ReservationRepository {

	

}
