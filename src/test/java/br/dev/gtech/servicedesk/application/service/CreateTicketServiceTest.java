package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidRequesterIdException;
import br.dev.gtech.servicedesk.application.exception.SectorIdNotFoundException;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTicketServiceTest {
    String TITLE = "Conserto de impressora";
    String DESCRIPTION = "impressora não está imprimindo colorido";
    UUID SECTOR_ID = UUID.randomUUID();
    UUID REQUESTER_ID = UUID.randomUUID();

    @Mock
    TickedRepositoryPort tickedRepositoryPort;

    @InjectMocks
    CreateTicketService createTicketService;

    @Test
    void shouldCreateAnTicket() {
        ArgumentCaptor<Ticket> captor = ArgumentCaptor.forClass(Ticket.class);

        createTicketService.execute(TITLE, DESCRIPTION, SECTOR_ID, REQUESTER_ID);

        verify(tickedRepositoryPort).save(captor.capture());

        Ticket savedTicket = captor.getValue();

        assertEquals(TITLE, savedTicket.getTitle());
        assertEquals(SECTOR_ID, savedTicket.getSectorId());

        verify(tickedRepositoryPort, times(1)).save(savedTicket);
    }

    @Test
    @DisplayName("Não deve criar ticket sem id do setor")
    void shouldNotCreateTicketWithInvalidSectorId() {
        assertThrows(SectorIdNotFoundException.class, () -> createTicketService.execute(TITLE,
                DESCRIPTION, null, REQUESTER_ID), "Id do setor não pode ser nullo");

        verify(tickedRepositoryPort, never()).save(any());
    }

    @Test
    @DisplayName("Não deve criar ticket sem id do requisitante")
    void shouldNotCreateTicketWithoutRequestId() {
        assertThrows(InvalidRequesterIdException.class, () -> createTicketService.execute(TITLE,
                DESCRIPTION, SECTOR_ID, null), "Id do requisitante não pode ser vazio");

        verify(tickedRepositoryPort, never()).save(any());
    }


}