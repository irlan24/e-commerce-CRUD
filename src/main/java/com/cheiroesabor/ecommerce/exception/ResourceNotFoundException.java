package com.cheiroesabor.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException{

    // A solicitação do usuário não existe no DB
    public ResourceNotFoundException(String message) {
        super(message);

    }
}
