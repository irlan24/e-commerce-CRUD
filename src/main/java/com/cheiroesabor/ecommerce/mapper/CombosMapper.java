package com.cheiroesabor.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.cheiroesabor.ecommerce.dto.request.CombosRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.CombosResponseDTO;
import com.cheiroesabor.ecommerce.infrastructure.entity.CombosEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CombosMapper {

    // Converte de Entity para DTO (response)
    CombosResponseDTO toDTO(CombosEntity combos);

    // Converte de DTO (request) para entity
    CombosEntity toEntity(CombosRequestDTO dto);

    // Converte uma List Entity para uma List DTO (response)
    List<CombosResponseDTO> toDTOList(List<CombosEntity> combos);

}
