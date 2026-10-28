package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;

@Validated
public record UpdateEmailDTO(
        @Email(message = "Você deve fornecer um email válido")
        String email
) {
}
