package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

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
        User foundedUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Usuário não encontrado"));

        User updatedUser = foundedUser.withEmail(email);

        userRepository.save(updatedUser);
    }
}
