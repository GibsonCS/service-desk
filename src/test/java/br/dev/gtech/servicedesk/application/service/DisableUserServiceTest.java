package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.UserAlreadyDisable;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisableUserServiceTest {
    private final UUID VALID_USERID = UUID.fromString("0e642fac-ae9f-4e47-9c75-1b2f6a6e2058");
    private final String VALID_NAME = "Gibson";
    private final String VALID_EMAIL = "gibson@example";
    private final String VALID_PASSWORD = "123456";
    private final Role VALID_ROLE = Role.USER;
    private final Boolean VALID_IS_ACTIVE = true;
    private final LocalDate VALID_UPDATED_AT = LocalDate.now();
    private final LocalDate VALID_CREATED_AT = LocalDate.now();

    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    DisableUserService disableUserService;

    @Test
    void shouldNotDisableUserIfNotExists() {
        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                disableUserService.disable(VALID_USERID), "Usuário não encontrado");

        verify(userRepositoryPort, times(1)).findById(VALID_USERID);
    }

    @Test
    void shouldNotDisableIfUserAlreadyDisable() {
        User user = User.create(VALID_USERID, VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                false, VALID_UPDATED_AT, VALID_CREATED_AT);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyDisable.class, () ->
                disableUserService.disable(VALID_USERID), "Usuário já está inativo");
    }

    @Test
    void shouldDisableUser() {
        User user = User.create(VALID_USERID, VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));

        disableUserService.disable(VALID_USERID);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(userRepositoryPort).save(captor.capture());

        User disabledUser = captor.getValue();

        assertEquals(false, disabledUser.getIsActive());
        assertEquals(user.getEmail(), disabledUser.getEmail());
        assertEquals(user.getId(), disabledUser.getId());
        assertEquals(user.getName(), disabledUser.getName());
    }
}