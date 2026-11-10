package br.dev.gtech.servicedesk.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDTO(
        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 6, max = 18, message = "A senha deve ter no mínimo 6 e no max 18 caracteres")
        String password,

        @NotBlank(message = "A senha não pode ser vazia")
        @Size(
                min = 6, max = 18,
                message = "A confirmação de senha deve ter no mínimo 6 e no max 18 caracteres")
        String confirmPassword
) {
}
