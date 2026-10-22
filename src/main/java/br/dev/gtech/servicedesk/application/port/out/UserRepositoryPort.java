package br.dev.gtech.servicedesk.application.port.out;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.User;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID userId);
    void save(User user);
}
