package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public record CreateUserDTO(

        @NotBlank(message = "Nome não pode está vazio")
        @Size(min = 3, message = "Nome deve ter mais de três caracteres")
        String name,

        @NotNull
        @Email
        String email,

        @NotNull
        @NotBlank(message = "Password não deve está vazio")
        @Size(min = 6, max = 12, message = "Senha deve ter entre 6 e 12 digítos")
        String password
) {
}
