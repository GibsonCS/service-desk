package br.dev.gtech.servicedesk.presentation.dto;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.User;

public record GetUserDTO(
        UUID id,
        String name,
        String email,
        Role role,
        Boolean isActive,
        Instant updatedAt,
        Instant createdAt
) {
    public static GetUserDTO toDTO(User user) {
        return new GetUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive(),
                user.getUpdatedAt(),
                user.getCreatedAt()
        );
    }
}
