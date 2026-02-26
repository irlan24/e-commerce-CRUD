package com.cheiroesabor.ecommerce.dto.response;

import java.math.BigDecimal;

import com.cheiroesabor.ecommerce.infrastructure.entity.CombosEntity;
import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;

import io.swagger.v3.oas.annotations.media.Schema;

public record CombosResponseDTO(
    @Schema(example = "1")
    Long id,

    @Schema(example = "COMBO_FESTA, COMBO_FAMILIA, COMBO_MINI, COMBO_PERSONALIZADO")
    CombosList comboList,

    @Schema(example = "Descrição conforme combo")
    String descricaoCombo,

    @Schema(example = "Preço conforme combo")
    BigDecimal precoCombo,

    @Schema(example = "Status conforme combo")
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