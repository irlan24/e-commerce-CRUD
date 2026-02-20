package com.cheiroesabor.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.exception.ResourceNotFoundException;
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
    public ClienteResponseDTO findById(Long id) {
        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id)); 
                
        return mapper.toDTO(cliente);

    }

    // Exemplo para buscar todos os clientes
    public List<ClienteResponseDTO> findAll() {
        List<ClientesEntity> clientes = repository.findAll();

        if(clientes.isEmpty()){
            throw new ResourceNotFoundException("Nenhum cliente encontrado");
        }

        return mapper.toDTOList(clientes); 
    }


    // Exemplo de método para salvar um cliente
    public ClienteResponseDTO salvar(ClienteRequestDTO dto){

        ClientesEntity cliente = mapper.toEntity(dto);

        cliente = repository.save(cliente);

        return mapper.toDTO(cliente);
        
    }

    // Exemplo de método para deletar um cliente
    public void deletar(Long id){

        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));

        repository.delete(cliente);
        
    }



}
