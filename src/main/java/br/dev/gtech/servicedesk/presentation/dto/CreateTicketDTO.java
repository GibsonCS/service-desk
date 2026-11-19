package br.dev.gtech.servicedesk.presentation.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTicketDTO(
        @Size(min = 5,  message = "O título deve ter no mínimo 5 caracteres")
        @NotBlank
        String title,

        @Size(min = 30, message = "A descrição deve ter no mínimo 30 caracteres")
        @NotBlank
        String description,

        @NotNull(message = "Id do setor é necessário")
        UUID sectorId,

        @NotNull(message = "Id do solicitante é necessário")
        UUID requesterId
) {
}
