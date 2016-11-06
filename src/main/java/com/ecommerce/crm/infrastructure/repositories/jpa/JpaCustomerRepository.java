package com.ecommerce.crm.infrastructure.repositories.jpa;

import com.ecommerce.crm.domain.Customer;
import com.ecommerce.crm.domain.CustomerRepository;
import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;


@DomainRepositoryImpl
public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{

}
