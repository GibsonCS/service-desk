package br.dev.gtech.servicedesk.application.service;

import java.time.LocalDate;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.User;

public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UUID create(String name, String email, String password) {
        if (userRepositoryPort.findByEmail(email).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        User user = User.create(
                UUID.randomUUID(),
                name,
                email,
                password,
                Role.USER,
                true,
                LocalDate.now(),
                LocalDate.now()
        );

        userRepositoryPort.save(user);

        return user.getId();
    }
}
