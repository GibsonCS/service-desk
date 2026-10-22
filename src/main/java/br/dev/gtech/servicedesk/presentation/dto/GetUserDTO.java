package br.dev.gtech.servicedesk.presentation.dto;

import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.Role;
import br.dev.gtech.servicedesk.domain.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
public record GetUserDTO(
        @NotNull
        @NotBlank(message = "O id do usuário é obrigatório")
        UUID id,
        String name,
        String email,
        Role role,
        Boolean isActive,
        LocalDate updatedAt,
        LocalDate createdAt


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
