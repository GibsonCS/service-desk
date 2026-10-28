package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.Role;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePasswordTest {
    private final UUID VALID_USERID = UUID.fromString("0e642fac-ae9f-4e47-9c75-1b2f6a6e2058");
    private final String VALID_NAME = "Gibson";
    private final String VALID_EMAIL = "gibson@example";
    private final String VALID_PASSWORD = "123456";
    private final Role VALID_ROLE = Role.USER;
    private final Boolean VALID_IS_ACTIVE = Boolean.TRUE;
    private final LocalDate VALID_UPDATED_AT = LocalDate.now();
    private final LocalDate VALID_CREATED_AT = LocalDate.now();

    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    UpdatePassword updatePassword;

    @Test
    void shouldExceptionIfUserNotFount(){
        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,() ->
                updatePassword.update(VALID_USERID, VALID_PASSWORD));

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

}