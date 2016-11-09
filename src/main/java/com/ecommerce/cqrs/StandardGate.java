package com.ecommerce.cqrs;

import com.ecommerce.cqrs.annotations.Command;
import com.ecommerce.cqrs.interfaces.IGate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class StandardGate implements IGate {
	
	@Inject
	private RunEnvironment runEnvironment;
	
	private GateLog gateHistory = new GateLog();

	@Override
	public Object dispatch(Object command){
		if (! gateHistory.register(command)){
//
			return null;
		}
			
		if (isAsynchronous(command)){
			return null;
		}
		
		
		return runEnvironment.run(command);
	}

	private boolean isAsynchronous(Object command) {
		if (! command.getClass().isAnnotationPresent(Command.class))
			return false;
		
		Command commandAnnotation = command.getClass().getAnnotation(Command.class);		
		return commandAnnotation.asynchronous();		
	}

	
}
