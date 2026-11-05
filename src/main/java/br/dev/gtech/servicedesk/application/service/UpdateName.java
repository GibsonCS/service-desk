package br.dev.gtech.servicedesk.application.service;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.in.UpdateNameUseCase;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;

public class UpdateName implements UpdateNameUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public UpdateName(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void update(UUID userId, String name) {
        Optional<User> foundedUser = userRepositoryPort.findById(userId);

        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado");
        }

        User updatedUser = foundedUser.get().withName(name);

        userRepositoryPort.save(updatedUser);
    }
}
