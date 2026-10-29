package br.dev.gtech.servicedesk.application.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.UpdatePasswordUseCase;
import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;

public class UpdatePassword implements UpdatePasswordUseCase {
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEnconder;

    public UpdatePassword(UserRepositoryPort userRepository, PasswordEncoderPort passwordEnconder) {
        this.userRepository = userRepository;
        this.passwordEnconder = passwordEnconder;
    }

    @Override
    public void update(UUID userId, String password, String confirmPassword) {
        String encodedPassword = passwordEnconder.encode(confirmPassword);

        if (!passwordEnconder.matches(password, encodedPassword)) {
            throw new RuntimeException("As senhas não conferem");
        }

        Optional<User> foundedUser = userRepository.findById(userId);

        if (foundedUser.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User updatedUser = User.create(
                foundedUser.get().getId(),
                foundedUser.get().getName(),
                foundedUser.get().getEmail(),
                encodedPassword, foundedUser.get().getRole(),
                foundedUser.get().getIsActive(),
                LocalDate.now(),
                foundedUser.get().getCreatedAt()
        );

        userRepository.save(updatedUser);
    }
}
