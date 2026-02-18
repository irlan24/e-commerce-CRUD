package com.cheiroesabor.ecommerce.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;

public interface ClienteRepository extends JpaRepository<ClientesEntity, Long>{

}
