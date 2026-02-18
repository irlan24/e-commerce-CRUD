package com.cheiroesabor.ecommerce.dto.response;

import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String email,
    String cell
) {
    // Construtor para converter a Entity em DTO facilmente
    public ClienteResponseDTO(ClientesEntity entity) {
        this(entity.getId(), entity.getNome(), entity.getEmail(), entity.getCell());
    }

   

    
}