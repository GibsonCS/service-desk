package br.dev.gtech.servicedesk.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.User;
import br.dev.gtech.servicedesk.infrastructure.entity.UserEntity;
import br.dev.gtech.servicedesk.infrastructure.mapper.UserMapper;
import br.dev.gtech.servicedesk.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        User user = UserMapper.toDomain(userEntity.get());

        return Optional.of(user);
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        userRepository.save(userEntity);
    }
}
