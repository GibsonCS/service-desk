package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.PasswordException;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.in.UpdatePasswordUseCase;
import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;

public class UpdatePassword implements UpdatePasswordUseCase {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public UpdatePassword(UserRepositoryPort userRepository, PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void update(UUID userId, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordException("As senhas não conferem");
        }

        User foundedUser = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Usuário não encontrado"));

        User updatedUser = foundedUser.withPasswordHash(passwordEncoderPort.encode(password));

        userRepository.save(updatedUser);
    }
}
