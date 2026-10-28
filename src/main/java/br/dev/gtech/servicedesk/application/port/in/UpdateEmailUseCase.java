package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

public interface UpdateEmailUseCase {
    void update(UUID userId, String email);
}
