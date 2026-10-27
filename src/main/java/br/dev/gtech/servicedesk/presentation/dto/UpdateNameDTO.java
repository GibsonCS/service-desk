package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Validated
public record UpdateNameDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Min(value = 3, message = "Nome deve ter 3 ou mais caracteres")
        @Max(value = 10, message = "Nome deve possuqir no máximo 10 caracteres")
        String name
) {
}
