package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.EmailAlreadyExists;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
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
        String normalizedEmail = email.trim().toLowerCase();

        User foundedUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Usuário não encontrado"));

        if (foundedUser.getEmail().equals(normalizedEmail)) {
            throw new EmailAlreadyExists(
                    "Você já utiliza este email! Favor insira um email diferente"
            );
        }

        userRepository.findByEmail(normalizedEmail).ifPresent(user -> {
            throw new EmailAlreadyExists("Email já cadastrado");
        });

        User updatedUser = foundedUser.withEmail(normalizedEmail);

        userRepository.save(updatedUser);
    }
}
