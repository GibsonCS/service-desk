package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.Ticket;

public interface GetTicketUseCase {
    Ticket execute(UUID ticketId);
}
