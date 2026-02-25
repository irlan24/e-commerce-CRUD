package com.cheiroesabor.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cheiroesabor.ecommerce.dto.request.CombosRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.CombosResponseDTO;
import com.cheiroesabor.ecommerce.services.CombosService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/combos")
public class CombosController {

    private final CombosService service;

    public CombosController(CombosService service) {
        this.service = service;
    }

    // Exemplo de endpoint para buscar um combo por ID
    @GetMapping("/{id}")
    public ResponseEntity<CombosResponseDTO> findById(@PathVariable Long id){

        CombosResponseDTO dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

    // Exemplo de endpoint para buscar todos os combo
    @GetMapping
    public ResponseEntity<List<CombosResponseDTO>> findAll(){

        List<CombosResponseDTO> listDto = service.findAll();

        return ResponseEntity.ok(listDto);
    }

    // Exemplo de endpoint para criar combo
    @PostMapping
    public ResponseEntity<CombosResponseDTO> create (@RequestBody @Valid CombosRequestDTO dto){
        CombosResponseDTO novoCombo = service.salvar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoCombo.id()) 
                    .toUri();

        return ResponseEntity.created(uri).body(novoCombo);

    }

}
