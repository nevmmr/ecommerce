package com.ecommerce.canonical.specification;

public class ConjunctionSpecification<T> extends CompositeSpecification<T>{
    private Specification<T>[] conjunction;

    @SuppressWarnings("unchecked")
	public ConjunctionSpecification(Specification<T>... conjunction){
        this.conjunction = conjunction;
    }

    public boolean isSatisfiedBy(T candidate){
        for (Specification<T> spec : conjunction){
        	if (!spec.isSatisfiedBy(candidate))
        		return false;
        }
    	
    	return true;
    }
}
