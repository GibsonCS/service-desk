package br.dev.gtech.servicedesk.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Sector {
    private final UUID id;
    private final String name;
    private final boolean isActive;
    private final Instant updatedAt;
    private final Instant createdAt;

    private Sector(UUID id, String name, boolean isActive, Instant updatedAt, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Sector create(UUID id, String name, boolean isActive, Instant updatedAt,
                                Instant createdAt) {
        return new Sector(id, name, isActive, updatedAt, createdAt);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
