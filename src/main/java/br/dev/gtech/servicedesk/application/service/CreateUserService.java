package br.dev.gtech.servicedesk.application.service;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.UserAlreadyExists;
import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.User;

public class CreateUserService implements CreateUserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoder;

    public CreateUserService(
            UserRepositoryPort userRepositoryPort,
            PasswordEncoderPort passwordEncoder
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UUID create(String name, String email, String password) {
        if (userRepositoryPort.findByEmail(email).isPresent()) {
            throw new UserAlreadyExists("Usuário já existe");
        }

        Instant now = Instant.now();
        User user = User.create(
                UUID.randomUUID(),
                name,
                email.toLowerCase(),
                passwordEncoder.encode(password),
                Role.USER,
                true,
                now,
                now
        );

        userRepositoryPort.save(user);

        return user.getId();
    }
}
