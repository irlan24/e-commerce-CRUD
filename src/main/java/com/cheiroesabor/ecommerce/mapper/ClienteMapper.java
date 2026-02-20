package com.cheiroesabor.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    

    // Converte de Entity para DTO (response)
    ClienteResponseDTO toDTO(ClientesEntity cliente);

    // Converte de DTO (request) para entity
    
    
    ClientesEntity toEntity(ClienteRequestDTO dto);

    // Converte uma List Entity para uma List DTO (response)
    List<ClienteResponseDTO> toDTOList(List<ClientesEntity> clientes);

    
    void updateEntityFromDto(ClienteRequestDTO dto, @MappingTarget ClientesEntity entity);
}

