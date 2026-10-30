package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;
import br.dev.gtech.servicedesk.infrastructure.security.PasswordEncoderAdapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateUserEntityServiceTest {
    String VALID_NAME = "Gibson";
    String VALID_EMAIL = "gibson@example.com";
    String VALID_PASSWORD = "1234567";

    CreateUserService createUserService;
    @Mock
    UserRepositoryPort userRepositoryPort;

    PasswordEncoderPort passwordEncoderPort;

    @BeforeEach
    void setup() {
        passwordEncoderPort = new PasswordEncoderAdapter();
        createUserService = new CreateUserService(userRepositoryPort, passwordEncoderPort);
    }

    @Test
    void shouldCreateUser() {
        Mockito
                .when(userRepositoryPort.findByEmail(VALID_EMAIL))
                .thenReturn(Optional.empty());

        UUID createdUserId = createUserService.create(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        verify(userRepositoryPort).save(captor.capture());

        User savedUser = captor.getValue();

        assertEquals(VALID_EMAIL, savedUser.getEmail());

        assertNotNull(createdUserId);
    }
}