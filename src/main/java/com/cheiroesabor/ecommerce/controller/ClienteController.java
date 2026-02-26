package com.cheiroesabor.ecommerce.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cheiroesabor.ecommerce.dto.request.ClienteRequestDTO;
import com.cheiroesabor.ecommerce.dto.response.ClienteResponseDTO;
import com.cheiroesabor.ecommerce.dto.update.ClienteUpdateDTO;
import com.cheiroesabor.ecommerce.services.ClientesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Endpoints responsáveis pelo gerenciamento dos clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    
    private final ClientesService service;

    // Injeção de dependência via construtor
    public ClienteController(ClientesService service){
        this.service = service;
    }

    
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados no sistema")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll() {

        List<ClienteResponseDTO> listDto = service.findAll();
        return ResponseEntity.ok(listDto);
    }

    
    @Operation(summary = "Busca cliente pelo ID", description = "Retorna o cadastro do cliente se o ID fornecido existir")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@Parameter(
        name = "id", 
        description = "Identificador do cliente", 
        example = "1",
        required = true
        ) @PathVariable  Long id) {
        ClienteResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    
    @Operation(summary = "Cria um novo cliente", description = "Cria o cadastro do cliente conforme os campos obrigatórios fornecidos na requisição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação nos campos enviados"),
        @ApiResponse(responseCode = "409", description = "Email já cadastrado em sistema")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Objeto contendo nome, email e demais dados do cliente",
        required = true
    ) @RequestBody @Valid ClienteRequestDTO dto) {
        ClienteResponseDTO novoCliente = service.salvar(dto);
        
        // URI (URL) de retorno de validação e representação
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(novoCliente.id()) 
                    .toUri();
                    
        return ResponseEntity.created(uri).body(novoCliente);
    }

    
    @Operation(summary = "Deleta um cliente", description = "Deleta o cliente se o ID correspondente existir")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente localizado e deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Dados não localizado pelo ID fornecido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        // .noContent() -> código de status HTTP para 204
        return ResponseEntity.noContent().build();
    }

    
    @Operation(summary = "Possibilita atualizar parcialmente um cliente", description = "Atualiza apenas os campos do cadastro fornecido na requisição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente localizado e dados atualizados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Dados não localizado pelo ID fornecido"),
        @ApiResponse(responseCode = "409", description = "Email já cadastrado no sistema")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarParcial(@PathVariable Long id, @RequestBody @Valid ClienteUpdateDTO dto){

        // código de status HTTP para 200
        return ResponseEntity.ok(service.patch(id, dto));
    }

    
    @Operation(summary = "Atualiza todos os dados de um cliente", description = "Atualiza completamente os dados de cadastro do cliente. Todos os campos obrigatórios devem ser enviados na requisição.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente localizado e dados atualizados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados incompletos para atualização do cliente"),
        @ApiResponse(responseCode = "404", description = "Dados não localizado pelo ID fornecido"),
        @ApiResponse(responseCode = "409", description = "Email já cadastrado no sistema")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO dto){

        // código de status HTTP para 200
        return ResponseEntity.ok(service.put(id, dto));
    }


}
