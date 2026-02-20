package com.cheiroesabor.ecommerce.infrastructure.handler;

import java.time.LocalDateTime;



public record MessageError(
    LocalDateTime hora,
    Integer status,
    String error,
    String mensagem,
    String path
) {}  