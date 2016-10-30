package com.ecommerce.system.application;



import org.springframework.stereotype.Component;

import com.ecommerce.canonical.models.AggregateId;


@Component
public class SystemContext {
	
	public SystemUser getSystemUser(){
		return new SystemUser(new AggregateId("1"));
	}
}
