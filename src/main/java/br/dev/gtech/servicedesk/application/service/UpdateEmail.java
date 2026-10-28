package br.dev.gtech.servicedesk.application.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.UpdateEmailUseCase;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;

public class UpdateEmail implements UpdateEmailUseCase {
    private final UserRepositoryPort userRepository;

    public UpdateEmail(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void update(UUID userId, String email) {
        Optional<User> foundedUser = userRepository.findById(userId);

        if (foundedUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User updatedUser = User.create(foundedUser.get().getId(), foundedUser.get().getName(),
                email, foundedUser.get().getHashPassword(), foundedUser.get().getRole(),
                foundedUser.get().getIsActive(), LocalDate.now(), foundedUser.get().getCreatedAt()
        );

        userRepository.save(updatedUser);
    }
}
