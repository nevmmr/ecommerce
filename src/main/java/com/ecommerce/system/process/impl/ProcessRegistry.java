package com.ecommerce.system.process.impl;

import java.util.Collection;

import com.ecommerce.system.process.ProcessInstance;
import com.ecommerce.system.process.ProcessManager;

public interface ProcessRegistry {

    @SuppressWarnings("rawtypes")
	Collection<ProcessManager> getLoadersForEvent(Object event);

    @SuppressWarnings("rawtypes")
	ProcessInstance createProcessInstance(Class<? extends ProcessInstance> processType);
}
