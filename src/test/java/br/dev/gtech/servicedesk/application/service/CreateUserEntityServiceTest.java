package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateUserEntityServiceTest {

    String VALID_NAME = "Gibson";
    String VALID_EMAIL = "gibson@example.com";
    String VALID_PASSWORD = "1234567";

    @InjectMocks
    CreateUserService createUserService;
    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    void shouldCreateUser() {
        Mockito
                .when(userRepositoryPort.findByEmail(VALID_EMAIL))
                .thenReturn(Optional.empty());

        UUID createdUserId = createUserService.create(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);

        Assertions.assertNotNull(createdUserId);
    }
}