package com.cheiroesabor.ecommerce.exception.handler;

import java.time.LocalDateTime;



public record MessageError(
    LocalDateTime hora,
    Integer status,
    String error,
    Object mensagem, // Aceita String ou List
    String path
) {}  