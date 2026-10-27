package br.dev.gtech.servicedesk.application.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

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
            throw new RuntimeException("Usuário não encontrado");
        }

        User updatedUser = User.create(
                foundedUser.get().getId(),
                name,
                foundedUser.get().getEmail(),
                foundedUser.get().getHashPassword(),
                foundedUser.get().getRole(),
                foundedUser.get().getIsActive(),
                LocalDate.now(),
                foundedUser.get().getCreatedAt()
        );

        userRepositoryPort.save(updatedUser);
    }
}
