package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Validated
public record UpdatePasswordDTO(
        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 6, max = 10, message = "A senha deve ter no mínimo 6 e no max 10 caracteres")
        String password,

        @NotBlank(message = "A senha não pode ser vazia")
        String confirmPassword
) {
}
