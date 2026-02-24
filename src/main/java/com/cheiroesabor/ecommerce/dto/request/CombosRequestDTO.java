package com.cheiroesabor.ecommerce.dto.request;

import com.cheiroesabor.ecommerce.infrastructure.entity.enums.CombosList;

import jakarta.validation.constraints.NotNull;


public record CombosRequestDTO(
    @NotNull(message = "O tipo de combo é obrigatório")
    CombosList comboList
) {}
