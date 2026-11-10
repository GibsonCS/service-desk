package br.dev.gtech.servicedesk.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSectorDTO(
        @NotBlank(message = "O nome não pode está vazio!")
        @Size(min = 3, max = 15, message = "Nome do setor deve ter entre 3 e 15 caracteres")
        String name
) {
}
