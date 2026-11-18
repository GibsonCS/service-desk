package br.dev.gtech.servicedesk.application.port.out;

import br.dev.gtech.servicedesk.domain.model.Ticket;

public interface TickedRepositoryPort {
    void save(Ticket ticket);
}
