package com.cheiroesabor.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import com.cheiroesabor.ecommerce.infrastructure.repository.ClienteRepository;
import com.cheiroesabor.ecommerce.mapper.ClienteMapper;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    private ClienteMapper mapper;

    // Exemplo de busca por ID);
    public ClienteResponseDTO findByID(Long id) {
        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id)); 
                
        return mapper.toDTO(cliente);

    }

    // Exemplo de método para buscar todos os clientes
    public List<ClienteResponseDTO> findAll() {
        List<ClientesEntity> clientes = repository.findAll();

        return mapper.toDTOList(clientes); 
    }


    // Exemplo de método para salvar um cliente
    public void salvar(ClienteRequestDTO dto){

        ClientesEntity cliente = mapper.toEntity(dto);

        repository.save(cliente);
        
    }

}
