package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.InvalidRequesterIdException;
import br.dev.gtech.servicedesk.application.exception.SectorIdNotFoundException;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.domain.model.Ticket;
import br.dev.gtech.servicedesk.domain.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTicketServiceTest {
    String TITLE = "Conserto de impressora";
    String DESCRIPTION = "impressora não está imprimindo colorido";
    UUID SECTOR_ID = UUID.randomUUID();
    UUID REQUESTER_ID = UUID.randomUUID();

    @Mock
    TickedRepositoryPort tickedRepositoryPort;

    @Mock
    SectorRepositoryPort sectorRepositoryPort;

    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    CreateTicketService createTicketService;

    @Mock
    User user;

    @Mock
    Sector sector;

    @Test
    @DisplayName("Deve criar um ticket")
    void shouldCreateAnTicket() {

        when(userRepositoryPort.findById(REQUESTER_ID)).thenReturn(Optional.of(user));
        when(sectorRepositoryPort.findById(SECTOR_ID)).thenReturn(Optional.of(sector));

        ArgumentCaptor<Ticket> captor = ArgumentCaptor.forClass(Ticket.class);

        createTicketService.execute(TITLE, DESCRIPTION, SECTOR_ID, REQUESTER_ID);

        verify(tickedRepositoryPort).save(captor.capture());

        Ticket savedTicket = captor.getValue();

        assertEquals(TITLE, savedTicket.getTitle());
        assertEquals(DESCRIPTION, savedTicket.getDescription());

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

    @Test
    @DisplayName("Não deve criar ticket de um requisitante inexistente")
    void shouldNotCreateTicketWithFoundedRequester() {

        when(userRepositoryPort.findById(REQUESTER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> createTicketService.execute(TITLE,
                DESCRIPTION, SECTOR_ID, REQUESTER_ID), "Id do requisitante não encontrado");

        verify(userRepositoryPort, times(1)).findById(REQUESTER_ID);
        verify(tickedRepositoryPort, never()).save(any());
    }

    @Test
    @DisplayName("Não deve criar ticket de um setor inexistente")
    void shouldNotCreateTicketWithNonExistentSector() {

        when(userRepositoryPort.findById(REQUESTER_ID)).thenReturn(Optional.of(user));
        when(sectorRepositoryPort.findById(SECTOR_ID)).thenReturn(Optional.empty());

        assertThrows(SectorIdNotFoundException.class, () -> createTicketService.execute(TITLE,
                DESCRIPTION, SECTOR_ID, REQUESTER_ID), "Id do setor não encontrado");

        verify(sectorRepositoryPort, times(1)).findById(SECTOR_ID);
        verify(tickedRepositoryPort, never()).save(any());
    }
}