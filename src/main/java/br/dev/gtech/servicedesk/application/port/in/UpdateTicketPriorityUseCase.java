package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;

public interface UpdateTicketPriorityUseCase {
    void execute(UUID ticketId, TicketPriority ticketPriority);
}
