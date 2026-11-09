package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DisableUserServiceTest {

    UUID VALID_ID = UUID.randomUUID();

    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    DisableUserService disableUserService;

    @Test
    void shouldNotDisableUserIfNotExists() {
        assertThrows(UserNotFoundException.class, () ->
                disableUserService.disable(VALID_ID), "Usuário não encontrado");
    }


}