package com.ecommerce.system.process.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;

import javax.inject.Inject;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ecommerce.system.process.ProcessInstance;
import com.ecommerce.system.process.ProcessManager;
import com.ecommerce.system.process.annotations.LoadProcess;
import com.ecommerce.system.process.annotations.ProcessAction;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

@SuppressWarnings({"rawtypes" })
@Component
public class SpringProcessRegistry implements ProcessRegistry, ApplicationListener<ContextRefreshedEvent> {

    private Multimap<Class<?>, String> loadersInterestedIn = HashMultimap.create();

    @Inject
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public Collection<ProcessManager> getLoadersForEvent(Object event) {
        Collection<ProcessManager> results = new HashSet<ProcessManager>();
        Collection<String> loadersBeansNames = loadersInterestedIn.get(event.getClass());
        for (String loaderBeanName : loadersBeansNames) {
            ProcessManager loader = beanFactory.getBean(loaderBeanName, ProcessManager.class);
            results.add(loader);
        }
        return results;
    }

    @Override
    public ProcessInstance createProcessInstance(Class<? extends ProcessInstance> processType) {
        return (ProcessInstance) beanFactory.getBean(processType);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadersInterestedIn.clear();
        registerProcessLoaderBeans();
    }

    private void registerProcessLoaderBeans() {
        String[] loadersNames = beanFactory.getBeanNamesForType(ProcessManager.class);
        for (String loaderBeanName : loadersNames) {
            BeanDefinition loaderBeanDefinition = beanFactory.getBeanDefinition(loaderBeanName);
            try {
                registerProcessLoader(Class.forName(loaderBeanDefinition.getBeanClassName()), loaderBeanName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void registerProcessLoader(Class<?> loaderClass, String beanName) {
        for (Method method : loaderClass.getMethods()) {
            if (method.getAnnotation(ProcessAction.class) != null || method.getAnnotation(LoadProcess.class) != null) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1) {
                    loadersInterestedIn.put(params[0], beanName);
                } else {
                    throw new RuntimeException("incorred event hadndler: " + method);
                }
            }
        }
    }

}
