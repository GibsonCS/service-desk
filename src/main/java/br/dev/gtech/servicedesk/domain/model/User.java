package br.dev.gtech.servicedesk.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.Role;

public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final String hashPassword;
    private final Role role;
    private final Boolean isActive;
    private final LocalDate updatedAt;
    private final LocalDate createdAt;

    public User(UUID id, String name, String email, String hashPassword, Role role,
                Boolean isActive, LocalDate updatedAt, LocalDate createdAt
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
                              Boolean isActive, LocalDate updatedAt, LocalDate createdAt
    ) {

        return new User(id, name, email, hashPassword, role, isActive, updatedAt, createdAt);
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

    public Boolean getActive() {
        return isActive;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
