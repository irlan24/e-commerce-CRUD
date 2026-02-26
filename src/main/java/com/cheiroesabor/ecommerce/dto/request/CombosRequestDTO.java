package com.cheiroesabor.ecommerce.dto.request;

import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;


public record CombosRequestDTO(

    @Schema(example = "COMBO_FESTA, COMBO_FAMILIA, COMBO_MINI, COMBO_PERSONALIZADO")
    @NotNull(message = "O tipo de combo é obrigatório")
    CombosList comboList
) {}
