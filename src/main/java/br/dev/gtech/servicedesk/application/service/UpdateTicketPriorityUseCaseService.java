package br.dev.gtech.servicedesk.application.service;

import java.util.Objects;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidTicketIdException;
import br.dev.gtech.servicedesk.application.exception.TicketNotFoundException;
import br.dev.gtech.servicedesk.application.exception.TicketPriorityException;
import br.dev.gtech.servicedesk.application.port.in.UpdateTicketPriorityUseCase;
import br.dev.gtech.servicedesk.application.port.out.TicketRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.model.Ticket;

public class UpdateTicketPriorityUseCaseService implements UpdateTicketPriorityUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;

    public UpdateTicketPriorityUseCaseService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public void execute(UUID ticketId, TicketPriority ticketPriority) {
        if (ticketId == null) {
            throw new InvalidTicketIdException("Id do ticket não deve ser vazio");
        }

        Ticket foundedTicket = ticketRepositoryPort.findById(ticketId).orElseThrow(() ->
                new TicketNotFoundException("Chamado não encontrado"));


        if (Objects.equals(foundedTicket.getPriority(), ticketPriority)) {
            throw new TicketPriorityException("Chamado já definido com a prioridade atual");
        }

        Ticket ticketWithChangedPriority = foundedTicket.withChangedPriority(ticketPriority);

        ticketRepositoryPort.save(ticketWithChangedPriority);
    }
}
