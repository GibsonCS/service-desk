package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.UserAlreadyDisable;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.in.DisableUserUseCase;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;

public class DisableUserService implements DisableUserUseCase {

    private final UserRepositoryPort userRepository;

    public DisableUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void disable(UUID userId) {
        User foundedUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Usuário não encontrado"));

        if (!foundedUser.getIsActive()) {
            throw new UserAlreadyDisable("Usuário já está inativo");
        }

        User disabledUser = foundedUser.disabled();

        userRepository.save(disabledUser);
    }
}
