package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.User;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GetUserServiceTest {
    private final UUID VALID_USERID = UUID.fromString("0e642fac-ae9f-4e47-9c75-1b2f6a6e2058");
    private final String VALID_NAME = "Gibson";
    private final String VALID_EMAIL = "gibson@example";
    private final String VALID_PASSWORD = "123456";
    private final Role VALID_ROLE = Role.USER;
    private final boolean VALID_IS_ACTIVE = Boolean.TRUE;
    private final Instant VALID_UPDATED_AT = Instant.now();
    private final Instant VALID_CREATED_AT = Instant.now();

    @Mock
    UserRepositoryPort userRepositoryPort;

    @InjectMocks
    GetUserService getUserService;

    @Test
    void shouldReturnAnUser() {
        User user = User.create(VALID_USERID, VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT);

        when(userRepositoryPort.findById(VALID_USERID)).thenReturn(Optional.of(user));

        User foundedUser = getUserService.get(VALID_USERID);

        Assertions.assertEquals(user.getId(), foundedUser.getId());
        Assertions.assertEquals(user.getEmail(), foundedUser.getEmail());
    }

}