package br.dev.gtech.servicedesk.domain.model;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.Role;

public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final String hashPassword;
    private final Role role;
    private final boolean isActive;
    private final Instant updatedAt;
    private final Instant createdAt;

    public User(UUID id, String name, String email, String hashPassword, Role role,
                boolean isActive, Instant updatedAt, Instant createdAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword;
        this.role = role;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static User create(UUID id, String name, String email, String hashPassword, Role role,
                              boolean isActive, Instant updatedAt, Instant createdAt
    ) {
        return new User(
                id,
                name,
                email,
                hashPassword,
                role,
                isActive,
                updatedAt,
                createdAt
        );
    }

    public User disabled() {
        return new User(
                id,
                name,
                email,
                hashPassword,
                role,
                false,
                Instant.now(),
                createdAt
        );
    }

    public User withName(String name) {
        return new User(
                id,
                name,
                email,
                hashPassword,
                role,
                isActive,
                Instant.now(),
                createdAt);
    }

    public User withEmail(String email) {
        return new User(
                id,
                name,
                email,
                hashPassword,
                role,
                isActive,
                Instant.now(),
                createdAt);
    }

    public User withPasswordHash(String passwordHash) {
        return new User(
                id,
                name,
                email,
                passwordHash,
                role,
                isActive,
                Instant.now(),
                createdAt);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
