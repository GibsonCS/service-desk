package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
public record UpdatePasswordDTO(
        @NotNull
        @NotBlank(message = "A senha não pode ser vazia")
        @Min(value = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password,

        @NotNull
        @NotBlank(message = "A senha não pode ser vazia")
        @Min(value = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String confirmPassword
) {
}
