package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Validated
public record UpdateNameDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(min = 3, message = "Nome deve ter 3 ou mais caracteres")
        String name
) {
}
