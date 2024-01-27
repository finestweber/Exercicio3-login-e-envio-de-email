package com.finestweber.exercicio3.dto;

import com.finestweber.exercicio3.entety.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UserRequest(
        @NotNull(message = "O campo NOME não pode ser null")
        @NotBlank(message = "O Campo NOME não pode ser vazio")
        String name,
        @NotNull(message = "O campo EMAIL não pode ser null")
        @NotBlank(message = "O Campo EMAIL não pode ser vazio")
        String email,
        @NotNull(message = "O campo SENHA não pode ser null")
        @NotBlank(message = "O Campo SENHA não pode ser vazio")
        String password
) {
    public Usuario toModel(){
        return new Usuario(name,email,password);
    }
}
