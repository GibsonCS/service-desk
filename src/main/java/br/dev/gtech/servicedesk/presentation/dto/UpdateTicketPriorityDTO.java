package br.dev.gtech.servicedesk.presentation.dto;

import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketPriorityDTO(
        @NotNull
        UUID id,

        @NotNull
        TicketPriority ticketPriority
) {
}
