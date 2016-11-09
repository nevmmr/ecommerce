package com.ecommerce.canonical.specification;

public class DisjunctionSpecification<T> extends CompositeSpecification<T>{
    private Specification<T>[] disjunction;

    @SuppressWarnings("unchecked")
	public DisjunctionSpecification(Specification<T>... disjunction){
        this.disjunction = disjunction;
    }

    public boolean isSatisfiedBy(T candidate){
        for (Specification<T> spec : disjunction){
        	if (spec.isSatisfiedBy(candidate))
        		return true;
        }
    	
    	return false;
    }
}
