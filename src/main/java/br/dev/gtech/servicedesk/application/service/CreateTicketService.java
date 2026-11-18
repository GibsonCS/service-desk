package br.dev.gtech.servicedesk.application.service;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;
import br.dev.gtech.servicedesk.domain.model.Ticket;

public class CreateTicketService implements CreateTicketUseCase {

    private final TickedRepositoryPort tickedRepositoryPort;

    public CreateTicketService(TickedRepositoryPort tickedRepositoryPort) {
        this.tickedRepositoryPort = tickedRepositoryPort;
    }

    @Override
    public UUID execute(String title, String description, UUID sectorId, UUID requesterId) {
        Instant now = Instant.now();

        Ticket ticket = Ticket.create(
                UUID.randomUUID(),
                title,
                description,
                TicketStatus.PENDENTE,
                null,
                requesterId,
                sectorId,
                null,
                now,
                null,
                null,
                null,
                null
        );

        tickedRepositoryPort.save(ticket);

        return ticket.getId();
    }
}
