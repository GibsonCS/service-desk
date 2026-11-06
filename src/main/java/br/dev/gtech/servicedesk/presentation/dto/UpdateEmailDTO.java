package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Validated
public record UpdateEmailDTO(
        @NotBlank(message = "O campo" + "email"  + "não pode ser vazio!")
        @Email(message = "Você deve fornecer um email válido")
        String email
) {
}
