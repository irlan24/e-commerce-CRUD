package com.cheiroesabor.ecommerce.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRequestDTO(
    @Schema(example = "nome cliente")
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @Schema(example = "nomeExemplo@dominioExemplo.com")
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    String email,

    @Schema(example = "71999998888")
    @NotBlank(message = "O celular é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O celular deve ter entre 10 e 11 dígitos")
    String telefone
) {}


















/*package com.cheiroesabor.ecommerce.dto.request;
import com.cheiroesabor.ecommerce.infrastructure.entity.ClientesEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "O e-mail é obrigatório") 
    @Email(message = "Formato de e-mail inválido")
    private String email;
    
    @NotBlank(message = "O celular é obrigatório")
    private String cell;


    public ClienteRequestDTO(@NotBlank(message = "O nome é obrigatório") String nome,
            @NotBlank(message = "O e-mail é obrigatório") @Email(message = "Formato de e-mail inválido") String email,
            @NotBlank(message = "O celular é obrigatório") String cell) {
        this.nome = nome;
        this.email = email;
        this.cell = cell;
    }

    public ClienteRequestDTO(ClientesEntity cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cell = cliente.getCell();
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }


}*/



