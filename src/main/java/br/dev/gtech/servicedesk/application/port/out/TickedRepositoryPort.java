package br.dev.gtech.servicedesk.application.port.out;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.Ticket;

public interface TickedRepositoryPort {
    void save(Ticket ticket);
    Optional<Ticket> findById(UUID ticketId);
}
