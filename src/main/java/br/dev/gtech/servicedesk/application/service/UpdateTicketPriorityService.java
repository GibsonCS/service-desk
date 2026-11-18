package br.dev.gtech.servicedesk.application.service;

import java.util.Objects;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidTickedIdException;
import br.dev.gtech.servicedesk.application.exception.TicketNotFoundException;
import br.dev.gtech.servicedesk.application.exception.TicketPriorityException;
import br.dev.gtech.servicedesk.application.port.in.UpdateTicketPriority;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.model.Ticket;

public class UpdateTicketPriorityService implements UpdateTicketPriority {
    private final TickedRepositoryPort tickedRepositoryPort;

    public UpdateTicketPriorityService(TickedRepositoryPort tickedRepositoryPort) {
        this.tickedRepositoryPort = tickedRepositoryPort;
    }

    @Override
    public void execute(UUID ticketId, TicketPriority ticketPriority) {
        if (ticketId == null) {
            throw new InvalidTickedIdException("Id do ticket não deve ser vazio");
        }

        Ticket foundedTicket = tickedRepositoryPort.findById(ticketId).orElseThrow(() ->
                new TicketNotFoundException("Chamado não encontrado"));


        if (Objects.equals(foundedTicket.getPriority(), ticketPriority)) {
            throw new TicketPriorityException("Chamado já classificado como");
        }

        Ticket ticketWithChangedPriority = foundedTicket.withChangedPriority(ticketPriority);

        tickedRepositoryPort.save(ticketWithChangedPriority);
    }
}
