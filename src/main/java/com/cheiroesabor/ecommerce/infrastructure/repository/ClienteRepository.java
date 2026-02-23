package com.cheiroesabor.ecommerce.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;

public interface ClienteRepository extends JpaRepository<ClientesEntity, Long>{

    // Verifica se o email existe no banco
    boolean existsByEmail(String email);

    // Verifica se o email existe para outro usuário
    boolean existsByEmailAndIdNot(String email, Long id);


}
