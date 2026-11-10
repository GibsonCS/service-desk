package br.dev.gtech.servicedesk.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(

        @NotBlank(message = "Nome não pode está vazio")
        @Size(min = 3, message = "Nome deve ter pelo menos três caracteres")
        String name,

        @NotBlank(message = "O campo" + "e-mail" + "não pode ser vazio!")
        @Email
        String email,

        @NotBlank(message = "Senha não deve está vazio")
        @Size(min = 6, max = 18, message = "Senha deve ter entre 6 e 18 digítos")
        String password
) {
}
