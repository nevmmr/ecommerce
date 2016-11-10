package com.ecommerce.system.process.impl;

import com.ecommerce.system.eventhandlers.EventHandler;
import com.ecommerce.system.events.SimpleEventPublisher;
import com.ecommerce.system.process.ProcessEngine;
import com.ecommerce.system.process.ProcessInstance;
import com.ecommerce.system.process.ProcessManager;
import com.ecommerce.system.process.annotations.LoadProcess;
import com.ecommerce.system.process.annotations.ProcessAction;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;


@Component
public class SimpleProcessEngine implements ProcessEngine {

    private final ProcessRegistry processRegistry;

    private final SimpleEventPublisher eventPublisher;

    @Inject
    public SimpleProcessEngine(ProcessRegistry ProcessRegistry, SimpleEventPublisher eventPublisher) {
        this.processRegistry = ProcessRegistry;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void registerEventHandler() {
        eventPublisher.registerEventHandler(new ProcessEventHandler(this));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void handleEvent(Object event) {
        Collection<ProcessManager> loaders = processRegistry.getLoadersForEvent(event);
        for (ProcessManager loader : loaders) {
            ProcessInstance ProcessInstance = loadProcess(loader, event);
            invokeProcessActionForEvent(ProcessInstance, event);
            if (ProcessInstance.isCompleted()) {
                loader.removeProcess(ProcessInstance);
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private ProcessInstance loadProcess(ProcessManager loader, Object event) {
        Class<? extends ProcessInstance> ProcessType = determineProcessTypeByLoader(loader);
        Object ProcessData = loadProcessData(loader, event);
        if (ProcessData == null) {
            ProcessData = loader.createNewProcessData();
        }
        ProcessInstance ProcessInstance = processRegistry.createProcessInstance(ProcessType);
        ProcessInstance.setData(ProcessData);
        return ProcessInstance;
    }

    // TODO determine Process type more reliably
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Class<? extends ProcessInstance> determineProcessTypeByLoader(ProcessManager loader) {
        Type type = ((ParameterizedType) loader.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return ((Class<? extends ProcessInstance>) type);
    }

    /**
     * TODO handle exception in more generic way
     */
    @SuppressWarnings("rawtypes")
	private Object loadProcessData(ProcessManager loader, Object event) {
        Method loaderMethod = findHandlerMethodForEvent(loader.getClass(), event);
        try {
            Object ProcessData = loaderMethod.invoke(loader, event);
            return ProcessData;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof NoResultException) {
                return null;
            } else {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeProcessActionForEvent(ProcessInstance<?> Process, Object event) {
        Method eventHandler = findHandlerMethodForEvent(Process.getClass(), event);
        try {
            eventHandler.invoke(Process, event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Method findHandlerMethodForEvent(Class<?> type, Object event) {
        for (Method method : type.getMethods()) {
            if (method.getAnnotation(ProcessAction.class) != null || method.getAnnotation(LoadProcess.class) != null) {
                if (method.getParameterTypes().length == 1
                        && method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                    return method;
                }
            }
        }
        throw new RuntimeException("no method handling " + event.getClass());
    }

    private static class ProcessEventHandler implements EventHandler {

        private final ProcessEngine processEngine;

        public ProcessEventHandler(ProcessEngine ProcessEngine) {
            this.processEngine = ProcessEngine;
        }

        @Override
        public boolean canHandle(Object event) {
            return true;
        }

        @Override
        public void handle(Object event) {
            processEngine.handleEvent(event);
        }
    }
}
