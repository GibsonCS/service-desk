package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.User;

public interface GetUserUseCase {
    User get(UUID userId);
}
