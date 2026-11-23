package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidTicketIdException;
import br.dev.gtech.servicedesk.application.exception.TicketNotFoundException;
import br.dev.gtech.servicedesk.application.port.out.TicketRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Ticket;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTicketServiceTest {
    UUID TICKET_ID = UUID.randomUUID();

    @Mock
    TicketRepositoryPort ticketRepositoryPort;

    @InjectMocks
    GetTicketService getTicketService;

    @Mock
    Ticket ticket;

    @Test
    @DisplayName("Não deve obter um chamado com id nullo")
    void shouldNotGetTicketWithNullId() {
        assertThrows(InvalidTicketIdException.class, () ->
                getTicketService.execute(null), "O ID do ticket não pode ser nulo.");

        verify(ticketRepositoryPort, never()).findById(any());
    }

    @Test
    @DisplayName("Não deve obter um chamado com id inexistente")
    void shouldNotGetTicketWithNoExistsTicketId() {
        assertThrows(TicketNotFoundException.class, () ->
                getTicketService.execute(TICKET_ID), "O ID do ticket não existe.");

        verify(ticketRepositoryPort, times(1)).findById(TICKET_ID);
    }

    @Test
    @DisplayName("Deve obter um ticket")
    void shouldGetTicket() {
        when(ticketRepositoryPort.findById(TICKET_ID)).thenReturn(Optional.of(ticket));

        Ticket ticket = getTicketService.execute(TICKET_ID);

        assertNotNull(ticket);
        verify(ticketRepositoryPort, times(1)).findById(TICKET_ID);
    }
}