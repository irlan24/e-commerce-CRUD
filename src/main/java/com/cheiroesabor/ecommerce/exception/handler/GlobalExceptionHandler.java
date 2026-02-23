package com.cheiroesabor.ecommerce.exception.handler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cheiroesabor.ecommerce.exception.BusinessException;
import com.cheiroesabor.ecommerce.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

        
        MessageError messageErro = new MessageError(
            LocalDateTime.now(), // Hora da captura
            HttpStatus.NOT_FOUND.value(),  // Código do erro
            HttpStatus.NOT_FOUND.getReasonPhrase(), // Erro
            ex.getMessage(), // Mensagem capturada no service
            request.getRequestURI()); // Path (URL)

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(messageErro);
    }

    // Exceção para tratar os @Valid nos DTO (Bad_request 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String errors = ex.getBindingResult() // Todos os erros gerais  do      provindo do JSON

                .getFieldErrors() //Retorna uma Lista com os Específicos (Preenchimentos errados do usuário)

                .stream() // Transforma a lista em fluxo, para processamento individual

                .map(err -> err.getField() + ": " + err.getDefaultMessage()) // formata cada Campo da lista (ex: email) + Msg inserida no DTO

                .collect(Collectors.joining(", ")); // Concatena tudo com separação por ", ". Ex de retorno: "email: deve ser um e-mail válido, nome: o campo não pode estar em branco".

        MessageError messageError = new MessageError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errors,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);

        }


        // Exceção para tratar as duplicidades de cadastro (PUT ou POST) (Bad_request 400)
        @ExceptionHandler(BusinessException.class)
        public ResponseEntity<MessageError> handleBusiness(
                BusinessException ex, HttpServletRequest request) {

        MessageError error = new MessageError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(error);

        // EXEMPLO DE USO
        // if(repository.existsByEmail(dto.email())){
                //throw new BusinessException("Email já cadastrado");}
}
        



}
