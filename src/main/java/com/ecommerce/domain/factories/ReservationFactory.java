package com.ecommerce.domain.factories;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.common.exceptions.DomainOperationException;
import com.ecommerce.ddd.annotations.domain.DomainFactory;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Reservation;
import com.ecommerce.domain.entity.Reservation.ReservationStatus;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import javax.inject.Inject;
import java.util.Date;

@DomainFactory
public class ReservationFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Reservation create(Client client){
		if (! canReserve(client))
			throw new DomainOperationException(client.getAggregateId(), "Client can not create reservations");
		
		Reservation reservation = new Reservation(AggregateId.generate(), ReservationStatus.OPENED, client.generateSnapshot(), new Date());
		spring.autowireBean(reservation);
		
		addGratis(reservation, client);
		
		return reservation;
	}

	private void addGratis(Reservation reservation, Client client) {
	}

	private boolean canReserve(Client client) {
		return true;
	}

}
