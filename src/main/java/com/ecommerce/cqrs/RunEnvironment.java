package com.ecommerce.cqrs;

import com.ecommerce.cqrs.interfaces.ICommandHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RunEnvironment {

	public interface HandlersProvider{
		ICommandHandler<Object, Object> getHandler(Object command);
	}
	
	@Inject
	private HandlersProvider handlersProfiver;
	
	public Object run(Object command) {		
		ICommandHandler<Object, Object> handler = handlersProfiver.getHandler(command);

		Object result = handler.handle(command);
		
		return result;
	}

}
