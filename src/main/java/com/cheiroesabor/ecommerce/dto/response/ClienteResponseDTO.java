package com.cheiroesabor.ecommerce.dto.response;

import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteResponseDTO(
    
    @Schema(example = "1")
    Long id,

    @Schema(example = "nome desejado")
    String nome,
    
    @Schema(example = "nomeExemplo@dominioExemplo.com")
    String email,

    @Schema(example = "71999998888")
    String telefone
) {
    // Construtor para converter a Entity em DTO facilmente
    public ClienteResponseDTO(ClientesEntity entity) {
        this(entity.getId(), entity.getNome(), entity.getEmail(), entity.getTelefone());
    }

   

    
}