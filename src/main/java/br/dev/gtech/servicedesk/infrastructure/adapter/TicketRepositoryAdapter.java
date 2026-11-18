package br.dev.gtech.servicedesk.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Ticket;
import br.dev.gtech.servicedesk.infrastructure.mapper.TicketMapper;
import br.dev.gtech.servicedesk.infrastructure.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryAdapter implements TickedRepositoryPort {

    private final TicketRepository ticketRepository;

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(TicketMapper.toEntity(ticket));
    }
}
