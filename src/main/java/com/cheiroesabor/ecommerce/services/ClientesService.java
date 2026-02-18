package com.cheiroesabor.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import com.cheiroesabor.ecommerce.infrastructure.repository.ClienteRepository;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    public ClienteResponseDTO findByID(Long id) {

        // Exemplo de busca por ID);
        ClientesEntity cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id)); 
                
        return new ClienteResponseDTO(cliente); // Retorne o DTO do cliente encontrado

    }

    // Exemplo de método para buscar todos os clientes
    public List<ClienteResponseDTO> findAll() {
        
        List<ClientesEntity> clientes = repository.findAll();
        return clientes.stream().map(ClienteResponseDTO::new).toList(); // Converte cada entidade para DTO
    }


    // Exemplo de método para salvar um cliente
    public void salvar(ClienteRequestDTO dto){

        ClientesEntity cliente = new ClientesEntity();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setCell(dto.cell());

        repository.save(cliente);
    }

}
