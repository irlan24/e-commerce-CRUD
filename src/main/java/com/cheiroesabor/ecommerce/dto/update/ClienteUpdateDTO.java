package com.cheiroesabor.ecommerce.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record ClienteUpdateDTO(
    @Schema(example = "nome cliente")
    String nome,

    @Schema(example = "nomeExemplo@dominioExemplo.com")
    @Email(message = "Formato de e-mail inválido")
    String email,
    
    @Schema(example = "71999998888")
    @Pattern(regexp = "\\d{10,11}", message = "O celular deve ter entre 10 e 11 dígitos")
    String telefone) {} 

