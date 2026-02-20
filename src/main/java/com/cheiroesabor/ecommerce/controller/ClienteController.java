package com.cheiroesabor.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.services.ClientesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    
    private final ClientesService service;

    // Injeção de dependência via construtor
    public ClienteController(ClientesService service){
        this.service = service;
    }

    // Exemplo de endpoint para buscar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll() {

        List<ClienteResponseDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    // Exemplo de endpoint para buscar um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable Long id) {
        ClienteResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    // Exemplo de endpoint para criar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody @Valid ClienteRequestDTO dto) {
        ClienteResponseDTO novoCliente = service.salvar(dto);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoCliente.id()) 
                    .toUri();
                    
        return ResponseEntity.created(uri).body(novoCliente);
    }

    // Exemplo de endpoint para deletar um cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
