package br.dev.gtech.servicedesk.application.service;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidRequesterIdException;
import br.dev.gtech.servicedesk.application.exception.SectorIdNotFoundException;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.TicketRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;
import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.domain.model.Ticket;
import br.dev.gtech.servicedesk.domain.model.User;

public class CreateTicketService implements CreateTicketUseCase {

    private final TicketRepositoryPort ticketRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final SectorRepositoryPort sectorRepositoryPort;

    public CreateTicketService(TicketRepositoryPort ticketRepositoryPort,
                               UserRepositoryPort userRepositoryPort, SectorRepositoryPort sectorRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.sectorRepositoryPort = sectorRepositoryPort;
    }

    @Override
    public UUID execute(String title, String description, UUID sectorId, UUID requesterId) {
        if (sectorId == null) {
            throw new SectorIdNotFoundException("Id do setor não pode ser nullo");
        }

        if (requesterId == null) {
            throw new InvalidRequesterIdException("Id do requisitante não pode ser vazio");
        }

        User foundedUser = userRepositoryPort.findById(requesterId).orElseThrow(() ->
                new UserNotFoundException("Id do requisitante não encontrado"));

        Sector foundedSector = sectorRepositoryPort.findById(sectorId).orElseThrow(() ->
                new SectorIdNotFoundException("Id do setor não encontrado"));

        Instant now = Instant.now();

        Ticket ticket = Ticket.create(
                UUID.randomUUID(),
                title,
                description,
                TicketStatus.PENDENTE,
                TicketPriority.BAIXA,
                foundedUser.getId(),
                foundedSector.getId(),
                null,
                now,
                null,
                null,
                null,
                null
        );

        ticketRepositoryPort.save(ticket);

        return ticket.getId();
    }
}
