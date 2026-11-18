package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}