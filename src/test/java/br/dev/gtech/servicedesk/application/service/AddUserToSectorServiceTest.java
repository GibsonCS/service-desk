package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.DisabledSectorException;
import br.dev.gtech.servicedesk.application.exception.DisabledUserException;
import br.dev.gtech.servicedesk.application.exception.SectorNotFoundedException;
import br.dev.gtech.servicedesk.application.exception.UserAlreadyAssociatedToSectorException;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.domain.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddUserToSectorServiceTest {
    private final UUID VALID_USERID = UUID.fromString("0e642fac-ae9f-4e47-9c75-1b2f6a6e2058");
    private final String VALID_NAME = "Gibson";
    private final String VALID_EMAIL = "gibson@example";
    private final String VALID_PASSWORD = "123456";
    private final Role VALID_ROLE = Role.USER;
    private final boolean VALID_IS_ACTIVE = true;
    private final Instant VALID_UPDATED_AT = Instant.now();
    private final Instant VALID_CREATED_AT = Instant.now();

    private final UUID VALID_SECTOR_ID = UUID.randomUUID();
    private final String VALID_SECTOR_NAME = "Financeiro";

    @Mock
    UserRepositoryPort userRepositoryPort;

    @Mock
    SectorRepositoryPort sectorRepositoryPort;


    @InjectMocks
    AddUserToSectorService addUserToSectorService;

    @Test
    @DisplayName("Não deve associar usuário inexistente a um setor")
    void shouldExceptionIfUserNotFound() {
        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                        addUserToSectorService.execute(VALID_USERID, VALID_SECTOR_ID),
                "Usuário não encontrado");

        verify(userRepositoryPort, times(1)).findById(VALID_USERID);
    }

    @Test
    @DisplayName("Não deve associar usuário a um setor inexistente")
    void shouldExceptionIfSectorNotExists() {
        User user = User.create(VALID_USERID, VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT, VALID_SECTOR_ID);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));
        when(sectorRepositoryPort.findById(VALID_SECTOR_ID)).thenReturn(Optional.empty());

        assertThrows(SectorNotFoundedException.class, () ->
                        addUserToSectorService.execute(VALID_USERID, VALID_SECTOR_ID),
                "Setor não encontrado");

        verify(userRepositoryPort, times(1)).findById(VALID_USERID);
        verify(sectorRepositoryPort, times(1)).findById(VALID_SECTOR_ID);
    }

    @Test
    @DisplayName("Não deve associar usuário inativo a um setor")
    void shouldNotAddDisabledUserToSector() {
        User user = User.create(VALID_USERID, VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                false, VALID_UPDATED_AT, VALID_CREATED_AT, VALID_SECTOR_ID);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));

        assertThrows(DisabledUserException.class, () ->
                        addUserToSectorService.execute(VALID_USERID, VALID_SECTOR_ID),
                "Não é possível adicionar um usuário inativo a um setor");

        verify(userRepositoryPort, times(1)).findById(VALID_USERID);
    }

    @Test
    @DisplayName("Não deve associar setor inativo a um usuário")
    void shouldNotAssociateSectorToDisabledSector() {
        User user = User.create(VALID_USERID, VALID_SECTOR_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT, VALID_SECTOR_ID);

        Sector sector = Sector.create(VALID_SECTOR_ID, VALID_NAME, false, VALID_UPDATED_AT,
                VALID_CREATED_AT);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));
        when(sectorRepositoryPort.findById(VALID_SECTOR_ID)).thenReturn(Optional.of(sector));

        assertThrows(DisabledSectorException.class, () ->
                        addUserToSectorService.execute(VALID_USERID, VALID_SECTOR_ID),
                "Não é possível adicionar um usuário a um setor inativo");

        verify(sectorRepositoryPort, times(1)).findById(VALID_SECTOR_ID);
    }

    @Test
    @DisplayName("Não deve associar usuário que já está associado ao setor")
    void shouldNotAssociateUserAlreadyAssociatedToSector() {
        User user = User.create(VALID_USERID, VALID_SECTOR_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT, VALID_SECTOR_ID);
        Sector sector = Sector.create(VALID_SECTOR_ID, VALID_NAME, VALID_IS_ACTIVE, VALID_UPDATED_AT,
                VALID_CREATED_AT);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));
        when(sectorRepositoryPort.findById(VALID_SECTOR_ID)).thenReturn(Optional.of(sector));

        assertThrows(UserAlreadyAssociatedToSectorException.class, () ->
                        addUserToSectorService.execute(VALID_USERID, VALID_SECTOR_ID),
                "Não é possível adicionar um usuário associado ao mesmo setor");

        verify(userRepositoryPort, times(1)).findById(VALID_USERID);
        verify(sectorRepositoryPort, times(1)).findById(VALID_SECTOR_ID);
    }

    @Test
    @DisplayName("Deve salvar um usuário com o id do setor")
    void shouldSaveUserWithSectorId() {
        User user = User.create(VALID_USERID, VALID_SECTOR_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT, null);

        Sector sector = Sector.create(VALID_SECTOR_ID, VALID_NAME, VALID_IS_ACTIVE, VALID_UPDATED_AT,
                VALID_CREATED_AT);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));
        when(sectorRepositoryPort.findById(VALID_SECTOR_ID)).thenReturn(Optional.of(sector));

        addUserToSectorService.execute(VALID_USERID, VALID_SECTOR_ID);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(userRepositoryPort).save(captor.capture());

        User userWithSectorId = captor.getValue();

        assertEquals(VALID_SECTOR_ID, userWithSectorId.getSectorId());

        verify(userRepositoryPort, times(1)).findById(VALID_USERID);
        verify(sectorRepositoryPort, times(1)).findById(VALID_SECTOR_ID);
    }
}