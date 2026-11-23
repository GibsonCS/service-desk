package br.dev.gtech.servicedesk.presentation.dto;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;
import br.dev.gtech.servicedesk.domain.model.Ticket;

public class GetTicketDTO {
    public UUID id;
    public String title;
    public String description;
    public TicketStatus status;
    public TicketPriority priority;
    public Instant createdAt;

    public GetTicketDTO(
            UUID id, String title, String description, TicketStatus status, TicketPriority priority,
            Instant createdAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public static GetTicketDTO toDTO(Ticket ticket) {
        return new GetTicketDTO(ticket.getId(), ticket.getTitle(), ticket.getDescription(),
                ticket.getStatus(), ticket.getPriority(), ticket.getCreatedAt());
    }
}
