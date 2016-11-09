package com.ecommerce.cqrs.interfaces;

/**
 * 
 * @author Nikolay
 *
 */
public interface IGate {

	public abstract Object dispatch(Object command);

}