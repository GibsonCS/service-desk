package br.dev.gtech.servicedesk.presentation.dto;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketPriorityDTO(
        @NotNull
        TicketPriority ticketPriority
) {
}
