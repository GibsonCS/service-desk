package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;

public interface CreateTicketUseCase {
    UUID execute(String title, String description, UUID sectorId, UUID requesterId);
}
