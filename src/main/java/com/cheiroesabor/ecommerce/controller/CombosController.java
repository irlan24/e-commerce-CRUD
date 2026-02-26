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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;





@Tag(name = "Combos", description = "Endpoints responsáveis pelo gerenciamento de combos")
@RestController
@RequestMapping("/combos")
public class CombosController {

    private final CombosService service;

    public CombosController(CombosService service) {
        this.service = service;
    }

    
    @Operation(summary = "Busca combo pelo ID", description = "Retorna o combo do cliente se o ID fornecido existir")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Combo localizado com sucesso"),
    @ApiResponse(responseCode = "404", description = "Dados não localizado pelo ID fornecido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CombosResponseDTO> findById(@Parameter(
        name = "id", 
        description = "Identificador do combo", 
        example = "1",
        required = true
        ) @PathVariable Long id){

        CombosResponseDTO dto = service.findById(id);

        return ResponseEntity.ok(dto);
    }

    
    @Operation(summary = "Lista todos os combos criados", description = "Retorna uma lista com todos os combos cadastrados no sistema")
    @GetMapping
    public ResponseEntity<List<CombosResponseDTO>> findAll(){

        List<CombosResponseDTO> listDto = service.findAll();

        return ResponseEntity.ok(listDto);
    }

    
    @Operation(summary = "Seleciona um combo", description = "Seleciona o combo conforme as opções disponíveis")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Combo escolhido com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
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
