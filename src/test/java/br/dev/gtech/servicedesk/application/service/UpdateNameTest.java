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
import br.dev.gtech.servicedesk.domain.model.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateNameTest {
    private final UUID VALID_USERID = UUID.fromString("0e642fac-ae9f-4e47-9c75-1b2f6a6e2058");
    private final String VALID_NAME = "Gibson";
    private final String VALID_EMAIL = "gibson@example";
    private final String VALID_PASSWORD = "123456";
    private final Role VALID_ROLE = Role.USER;
    private final Boolean VALID_IS_ACTIVE = Boolean.TRUE;
    private final LocalDate VALID_UPDATED_AT = LocalDate.now();
    private final LocalDate VALID_CREATED_AT = LocalDate.now();

    @Mock
    UserRepositoryPort userRepository;

    @InjectMocks
    UpdateName updateName;

    @Test
    void shouldReturnNoContentStausCode() {
        User user = User.create(VALID_USERID, VALID_NAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLE,
                VALID_IS_ACTIVE, VALID_UPDATED_AT, VALID_CREATED_AT);

        when(userRepository.findById(VALID_USERID)).thenReturn(Optional.of(user));

        updateName.update(VALID_USERID, "NewName");

        verify(userRepository, times(1)).save(any());
    }
}