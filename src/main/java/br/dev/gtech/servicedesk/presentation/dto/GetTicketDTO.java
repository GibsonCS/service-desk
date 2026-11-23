package br.dev.gtech.servicedesk.presentation.dto;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;
import br.dev.gtech.servicedesk.domain.model.Ticket;

public record GetTicketDTO(
        UUID id,
        String title,
        String description,
        TicketStatus status,
        TicketPriority priority,
        UUID requesterId,
        UUID sectorId,
        Instant createdAt
) {
    public static GetTicketDTO toDTO(Ticket ticket) {
        return new GetTicketDTO(
                ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus(),
                ticket.getPriority(), ticket.getRequesterId(), ticket.getSectorId(),
                ticket.getCreatedAt());
    }
}
