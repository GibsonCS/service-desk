package br.dev.gtech.servicedesk.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmailDTO(
        @NotBlank(message = "O campo" + "email" + "não pode ser vazio!")
        @Email(message = "Você deve fornecer um email válido")
        String email
) {
}
