package br.dev.gtech.servicedesk.infrastructure.mapper;

import br.dev.gtech.servicedesk.domain.model.Ticket;
import br.dev.gtech.servicedesk.infrastructure.entity.TicketEntity;

public class TicketMapper {

    public static TicketEntity toEntity(Ticket ticket) {
        return new TicketEntity(
                ticket.getId(), ticket.getTitle(), ticket.getDescription(),
                ticket.getStatus(), ticket.getPriority(), ticket.getRequesterId(),
                ticket.getSectorId(), ticket.getAssigneeId(), ticket.getCreatedAt(),
                ticket.getFirstStartedAt(), ticket.getResolvedAt(), ticket.getCancelledAt(),
                ticket.getArchivedAt()
        );
    }

    public static Ticket toDomain(TicketEntity ticketEntity) {
        return Ticket.create(
                ticketEntity.getId(), ticketEntity.getTitle(), ticketEntity.getDescription(),
                ticketEntity.getStatus(), ticketEntity.getPriority(), ticketEntity.getRequesterId(),
                ticketEntity.getSectorId(), ticketEntity.getAssigneeId(),
                ticketEntity.getCreatedAt(), ticketEntity.getFirstStartedAt(),
                ticketEntity.getResolvedAt(), ticketEntity.getCancelledAt(),
                ticketEntity.getArchivedAt());
    }
}
