package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidTickedIdException;
import br.dev.gtech.servicedesk.application.exception.TicketNotFoundException;
import br.dev.gtech.servicedesk.application.port.in.GetTicketUseCase;
import br.dev.gtech.servicedesk.application.port.out.TicketRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Ticket;

public class GetTicketService implements GetTicketUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;

    public GetTicketService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public Ticket execute(UUID ticketId) {
        if (ticketId == null) {
            throw new InvalidTickedIdException("O ID do ticket não pode ser nullo.");
        }

        return ticketRepositoryPort.findById(ticketId).orElseThrow(() ->
                new TicketNotFoundException("O ID do ticket não existe."));
    }
}
