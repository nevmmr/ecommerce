package com.ecommerce.canonical.specification;

public interface Specification<T> {
	public boolean isSatisfiedBy(T candidate);

	public Specification<T> and(Specification<T> other);

	public Specification<T> or(Specification<T> other);
	
	@SuppressWarnings("unchecked")
	public Specification<T> conjunction(Specification<T>... others);

	public Specification<T> not();
}
