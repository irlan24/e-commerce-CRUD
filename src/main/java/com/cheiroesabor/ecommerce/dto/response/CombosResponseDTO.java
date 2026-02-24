package com.cheiroesabor.ecommerce.dto.response;

import java.math.BigDecimal;

import com.cheiroesabor.ecommerce.infrastructure.entity.CombosEntity;
import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;

public record CombosResponseDTO(
    Long id,
    CombosList comboList,
    String descricaoCombo,
    BigDecimal precoCombo,
    Boolean statusCombo
) {
    // Construtor para converter a Entity em DTO facilmente
    public CombosResponseDTO(CombosEntity entity) {
        this(
            entity.getId(),
            entity.getComboList(), 
            entity.getDescricaoCombo(), 
            entity.getPrecoCombo(), 
            entity.getStatusCombo()
        );
    }
}