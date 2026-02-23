package com.cheiroesabor.ecommerce.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record ClienteUpdateDTO(
    String nome,

    @Email(message = "Formato de e-mail inválido")
    String email,
    
    @Pattern(regexp = "\\d{10,11}", message = "O celular deve ter entre 10 e 11 dígitos")
    String telefone) {} 

