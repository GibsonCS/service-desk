package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

public interface CreateUserUseCase {
    public UUID create(String name, String email, String password);
}
