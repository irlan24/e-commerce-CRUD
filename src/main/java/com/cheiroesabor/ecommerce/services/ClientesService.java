package com.cheiroesabor.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.dto.update.ClienteUpdateDTO;
import com.cheiroesabor.ecommerce.exception.BusinessException;
import com.cheiroesabor.ecommerce.exception.ResourceNotFoundException;
import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import com.cheiroesabor.ecommerce.infrastructure.repository.ClienteRepository;
import com.cheiroesabor.ecommerce.mapper.ClienteMapper;

@Service
public class ClientesService {

    
    private ClienteRepository repository;
    private ClienteMapper mapper;

    // Construtor para injeção de dependências
    public ClientesService(ClienteRepository repository, ClienteMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    // Exemplo de busca por ID);
    public ClienteResponseDTO findById(Long id) {
        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id)); 
                
        return mapper.toDTO(cliente);

    }

    // Exemplo para buscar todos os clientes
    public List<ClienteResponseDTO> findAll() {
        List<ClientesEntity> clientes = repository.findAll();
        
        return mapper.toDTOList(clientes); 
    }


    // Exemplo de método para salvar um cliente NOVO
    public ClienteResponseDTO salvar(ClienteRequestDTO dto){

        if(repository.existsByEmail(dto.email())){
                throw new BusinessException("Email já cadastrado");}

        ClientesEntity cliente = mapper.toEntity(dto);

        cliente = repository.save(cliente);
 
        return mapper.toDTO(cliente);
        
    }

    // Exemplo de método para deletar um cliente
    public void deletar(Long id){

        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));

        repository.delete(cliente);
        
    }
    // Exemplo de método para atualizar parcialmente um cliente (PATCH)
    public ClienteResponseDTO patch(Long id, ClienteUpdateDTO dto){
        
        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não identificado para atualização"));

        // valida apenas se o email foi enviado
        if(dto.email() != null && repository.existsByEmailAndIdNot(dto.email(), id)){
            throw new BusinessException("Email já cadastrado para outro cliente");
        }

        mapper.partialUpdate(dto, cliente);

        repository.save(cliente);

        return mapper.toDTO(cliente);
    }


    // Exemplo de método para atualizar completamente um cliente (PUT)
    public ClienteResponseDTO put(Long id, ClienteRequestDTO dto){
        
        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não identificado para atualização"));

        if(repository.existsByEmailAndIdNot(dto.email(), id)){
            throw new BusinessException("Email cadastrado para outro cliente");
        }

        mapper.updateEntityFromDto(dto, cliente);
        
        repository.save(cliente);

        return mapper.toDTO(cliente);
    }



}
