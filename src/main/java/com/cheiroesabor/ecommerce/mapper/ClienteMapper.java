package com.cheiroesabor.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toDTO(ClientesEntity cliente);

    ClientesEntity toEntity(ClienteRequestDTO dto);

    List<ClienteResponseDTO> toDTOList(List<ClientesEntity> clientes);

    void updateEntityFromDto(ClienteRequestDTO dto, @MappingTarget ClientesEntity entity);
}

