package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

public interface DisableUserUseCase {
    void disable(UUID userId);
}
