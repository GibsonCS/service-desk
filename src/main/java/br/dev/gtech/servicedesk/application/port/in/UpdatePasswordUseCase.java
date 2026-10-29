package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

public interface UpdatePasswordUseCase {
    void update(UUID userId, String password, String confirmPassword);
}
