package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidTickedIdException;
import br.dev.gtech.servicedesk.application.exception.TicketNotFoundException;
import br.dev.gtech.servicedesk.application.exception.TicketPriorityException;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;
import br.dev.gtech.servicedesk.domain.model.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTicketPriorityServiceTest {
    UUID TICKED_ID = UUID.randomUUID();
    TicketPriority TICKET_PRIORITY = TicketPriority.CRITICA;

    @Mock
    TickedRepositoryPort tickedRepositoryPort;

    @InjectMocks
    UpdateTicketPriorityService updateTicketPriorityService;


    Ticket ticket;

    @BeforeEach
    void setup() {
        ticket = Ticket.create(TICKED_ID,                          // id
                "Erro ao gerar relatório financeiro",
                "O sistema retorna erro 500 ao exportar.",
                TicketStatus.PENDENTE,
                TICKET_PRIORITY,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                Instant.now().minus(Duration.ofHours(2)),
                Instant.now().minus(Duration.ofHours(1)),
                null,
                null,
                null);
    }

    @Test
    @DisplayName("Não deve atualizar prioridade com id do ticket invalido")
    void shouldNotUpdatePriorityWithInvalidTicketId() {
        assertThrows(InvalidTickedIdException.class, () ->
                        updateTicketPriorityService.execute(null, TICKET_PRIORITY),
                "Id do ticket não deve ser vazio");

        verify(tickedRepositoryPort, never()).save(ticket);
    }

    @Test
    @DisplayName("Não deve atualizar prioridade de um ticket inexistente")
    void shouldNotUpdateNonExistsTicket() {
        assertThrows(TicketNotFoundException.class, () ->
                        updateTicketPriorityService.execute(TICKED_ID, TICKET_PRIORITY),
                "Chamado não encontrado");

        verify(tickedRepositoryPort, times(1)).findById(TICKED_ID);
    }

    @Test
    @DisplayName("Não deve atualizar prioridade de um ticket com o mesmo status de prioridade")
    void shouldNotUpdateTicketPriorityWithEqualsStatus() {
        when(tickedRepositoryPort.findById(TICKED_ID)).thenReturn(Optional.of(ticket));

        assertThrows(TicketPriorityException.class, () ->
                        updateTicketPriorityService.execute(TICKED_ID, TICKET_PRIORITY),
                "Chamado já classificado como");

        verify(tickedRepositoryPort, times(1)).findById(TICKED_ID);
    }

    @Test
    @DisplayName("Deve atualizar prioridade de um ticket")
    void shouldUpdateTicketPriority() {
        when(tickedRepositoryPort.findById(TICKED_ID)).thenReturn(Optional.of(ticket));

        updateTicketPriorityService.execute(TICKED_ID, TicketPriority.BAIXA);

        ArgumentCaptor<Ticket> captor = ArgumentCaptor.forClass(Ticket.class);
        verify(tickedRepositoryPort).save(captor.capture());

        Ticket savedTicket = captor.getValue();

        assertEquals(TicketPriority.BAIXA, savedTicket.getPriority());

        verify(tickedRepositoryPort, times(1)).save(savedTicket);
        verify(tickedRepositoryPort, times(1)).findById(TICKED_ID);
    }
}

