package com.customer.api.repository;

import com.customer.api.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCpfCnpj(@Param("cpfCnpj") final String cpfCnpj);
}