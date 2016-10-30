package com.ecommerce.system.application;

import com.ecommerce.canonical.models.AggregateId;

public class SystemUser {

	private AggregateId clientId;
		
	SystemUser(AggregateId clientId) {
		this.clientId = clientId;
	}

	/**
	 * 
	 * @return Domain model Client
	 */
	public AggregateId getClientId(){
		return clientId;
	}
	
	public boolean isLoogedIn(){
		return clientId != null;
	}
}
