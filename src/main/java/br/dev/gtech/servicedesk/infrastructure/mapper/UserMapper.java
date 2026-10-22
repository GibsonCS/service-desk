package br.dev.gtech.servicedesk.infrastructure.mapper;

import br.dev.gtech.servicedesk.domain.model.User;
import br.dev.gtech.servicedesk.infrastructure.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getHashPassword(),
                userEntity.getRole(),
                userEntity.getIsActive(),
                userEntity.getUpdatedAt(),
                userEntity.getCreatedAt()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getHashPassword(),
                user.getRole(),
                user.getIsActive(),
                user.getUpdatedAt(),
                user.getCreatedAt()
        );
    }
}
