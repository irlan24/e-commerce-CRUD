package com.cheiroesabor.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.services.ClientesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService service;

    // Exemplo de endpoint para buscar todos os clientes
    @GetMapping
    public List<ClienteResponseDTO> findAll() {
        return service.findAll();
    }

    // Exemplo de endpoint para buscar um cliente por ID
    @GetMapping("/{id}")
    public ClienteResponseDTO getClienteById(@PathVariable Long id) {
        return service.findByID(id);
    }

    // Exemplo de endpoint para criar um novo cliente
    @PostMapping //lê o JSON da requisição (@RequestBody) e ativa as validações do DTO (@Valid)
    public ResponseEntity<Void> criarCliente(@RequestBody @Valid ClienteRequestDTO dto){
        service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

   


}
