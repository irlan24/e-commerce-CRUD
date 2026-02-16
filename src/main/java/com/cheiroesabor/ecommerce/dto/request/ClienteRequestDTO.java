package com.cheiroesabor.ecommerce.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {

    

    @Column(nullable = false)
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(unique = true, nullable = false) 
    @NotBlank(message = "O e-mail é obrigatório") 
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Column(nullable = false) 
    @NotBlank(message = "O celular é obrigatório")
    private String cell;






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


}
