package br.dev.gtech.servicedesk.application.port.out;

import java.util.Optional;

import br.dev.gtech.servicedesk.domain.model.User;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    void save(User user);
}
