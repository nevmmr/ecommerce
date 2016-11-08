package com.ecommerce.application.services;

import com.ecommerce.application.commands.ShowOrderDetailsCommand;
import com.ecommerce.application.offer.OfferChangedException;
import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.common.exceptions.DomainOperationException;
import com.ecommerce.ddd.annotations.app.GeneralApplicationService;
import com.ecommerce.domain.entity.*;
import com.ecommerce.domain.factories.DiscountFactory;
import com.ecommerce.domain.factories.PurchaseFactory;
import com.ecommerce.domain.factories.ReservationFactory;
import com.ecommerce.domain.offer.DiscountPolicy;
import com.ecommerce.domain.repositories.*;
import com.ecommerce.domain.services.SuggestionService;
import com.ecommerce.domain.vo.Offer;
import com.ecommerce.system.application.SystemContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@GeneralApplicationService
public class OrderingServiceImpl implements IOrderingService {

	@Inject
	private SystemContext systemContext;

	@Inject
	private ClientRepository clientRepository;

	@Inject
	private ReservationRepository reservationRepository;

	@Inject
	private ReservationFactory reservationFactory;

	@Inject
	private PurchaseFactory purchaseFactory;

	@Inject
	private PurchaseRepository purchaseRepository;

	@Inject
	private ProductRepository productRepository;

	@Inject
	private PaymentRepository paymentRepository;

	@Inject
	private DiscountFactory discountFactory;

	@Inject
	private SuggestionService suggestionService;

	public AggregateId createOrder() {
		Reservation reservation = reservationFactory.create(loadClient());
		reservationRepository.save(reservation);
		return reservation.getAggregateId();
	}

	@Override
	public void addProduct(AggregateId orderId, AggregateId productId, int quantity) {
		Reservation reservation = reservationRepository.load(orderId);

		Product product = productRepository.load(productId);

		if (!product.isAvailabe()) {
			Client client = loadClient();
			product = suggestionService.suggestEquivalent(product, client);
		}

		reservation.add(product, quantity);

		reservationRepository.save(reservation);
	}

	public Offer calculateOffer(AggregateId orderId) {
		Reservation reservation = reservationRepository.load(orderId);

		DiscountPolicy discountPolicy = discountFactory.create(loadClient());

		return reservation.calculateOffer(discountPolicy);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void confirm(AggregateId orderId, ShowOrderDetailsCommand orderDetailsCommand, Offer seenOffer) {
		Reservation reservation = reservationRepository.load(orderId);
		if (reservation.isClosed())
			throw new DomainOperationException(reservation.getAggregateId(), "reservation is already closed");

		Offer newOffer = reservation.calculateOffer(discountFactory.create(loadClient()));

		if (!newOffer.sameAs(seenOffer, 5))
			throw new OfferChangedException(reservation.getAggregateId(), seenOffer, newOffer);

		Client client = loadClient();
		Purchase purchase = purchaseFactory.create(reservation.getAggregateId(), client, seenOffer);

		if (!client.canAfford(purchase.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");

		purchaseRepository.save(purchase);
		Payment payment = client.charge(purchase.getTotalCost());
		paymentRepository.save(payment);

		purchase.confirm();
		reservation.close();

		reservationRepository.save(reservation);
		clientRepository.save(client);

	}

	private Client loadClient() {
		return clientRepository.load(systemContext.getSystemUser().getClientId());
	}
}
