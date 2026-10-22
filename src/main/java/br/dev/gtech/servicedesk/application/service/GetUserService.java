package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.GetUserUseCase;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;

public class GetUserService implements GetUserUseCase {
    private final UserRepositoryPort userRepository;

    public GetUserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));
    }
}
