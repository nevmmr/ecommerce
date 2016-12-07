package com.ecommerce.application.commands.handlers;

import javax.inject.Inject;

import com.ecommerce.application.commands.AddProductToCartCommand;
import com.ecommerce.application.commands.RemoveProductFromCartCommand;
import com.ecommerce.cqrs.annotations.CommandHandlerAnnotation;
import com.ecommerce.cqrs.interfaces.ICommandHandler;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.entity.Product;
import com.ecommerce.domain.entity.Reservation;
import com.ecommerce.domain.repositories.ClientRepository;
import com.ecommerce.domain.repositories.ProductRepository;
import com.ecommerce.domain.repositories.ReservationRepository;
import com.ecommerce.domain.services.SuggestionService;
import com.ecommerce.system.application.SystemContext;


@CommandHandlerAnnotation
public class RemoveProductFromCartCommandHandler implements ICommandHandler<RemoveProductFromCartCommand, Void>{

	@Inject
	private ReservationRepository reservationRepository;
	
	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private SuggestionService suggestionService;
	
	@Inject
	private ClientRepository clientRepository;
	
	@Inject
	private SystemContext systemContext;
	
	@Override
	public Void handle(RemoveProductFromCartCommand command) {
		Reservation reservation = reservationRepository.load(command.getOrderId());
		
		Product product = productRepository.load(command.getProductId());
		
		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}
		reservation.add(product, command.getQuantity());
		
		reservationRepository.save(reservation);
		
		return null;
	}
	
	private Client loadClient() {
		return clientRepository.load(systemContext.getSystemUser().getClientId());
	}

}
