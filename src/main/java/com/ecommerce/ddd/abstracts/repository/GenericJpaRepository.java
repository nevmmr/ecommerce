package com.ecommerce.ddd.abstracts.repository;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class GenericJpaRepository<A extends BaseAggregateRoot> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<A> clazz;
    
    @Inject
    private AutowireCapableBeanFactory spring;

    @SuppressWarnings("unchecked")
    public GenericJpaRepository() {
        this.clazz = ((Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public A load(AggregateId id) {
        A aggregate = entityManager.find(clazz, id, LockModeType.OPTIMISTIC);
        
        if (aggregate == null)
        	throw new RuntimeException("Aggregate " + clazz.getCanonicalName() + " id = " + id + " does not exist");
        
        if (aggregate.isRemoved())
        	throw new RuntimeException("Aggragate + " + id + " is removed.");
        
        spring.autowireBean(aggregate);
        
        return aggregate;
    }

    public void save(A aggregate) {
    	if (entityManager.contains(aggregate)){
    		entityManager.lock(aggregate, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    	}
    	else{
    	    entityManager.persist(aggregate);
    	}
    }
    
    public void delete(AggregateId id){
		A entity = load(id);
		entity.markAsRemoved();					
	}
}
