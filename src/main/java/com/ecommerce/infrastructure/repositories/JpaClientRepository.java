package com.ecommerce.infrastructure.repositories;

import com.ecommerce.ddd.abstracts.repository.GenericJpaRepository;
import com.ecommerce.ddd.annotations.domain.DomainRepositoryImpl;
import com.ecommerce.domain.entity.Client;
import com.ecommerce.domain.repositories.ClientRepository;


@DomainRepositoryImpl
public class JpaClientRepository extends GenericJpaRepository<Client> implements ClientRepository{

}
