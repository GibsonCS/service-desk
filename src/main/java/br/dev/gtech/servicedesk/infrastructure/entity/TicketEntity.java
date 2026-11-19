package br.dev.gtech.servicedesk.infrastructure.entity;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_TICKET")
public class TicketEntity {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;
    @Column(name = "requester_id", nullable = false)
    private UUID requesterId;
    @Column(name = "sector_id", nullable = false)
    private UUID sectorId;
    @Column(name = "assignee_id")
    private UUID assigneeId;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "first_started_at")
    private Instant firstStartedAt;
    @Column(name = "resolved_at")
    private Instant resolvedAt;
    @Column(name = "cancelled_at")
    private Instant cancelledAt;
    @Column(name = "archived_at")
    private Instant archivedAt;

    public TicketEntity() {
    }

    public TicketEntity(
            UUID id, String title, String description, TicketStatus status, TicketPriority priority,
            UUID requesterId, UUID sectorId, UUID assigneeId, Instant createdAt,
            Instant firstStartedAt, Instant resolvedAt, Instant cancelledAt, Instant archivedAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.requesterId = requesterId;
        this.sectorId = sectorId;
        this.assigneeId = assigneeId;
        this.createdAt = createdAt;
        this.firstStartedAt = firstStartedAt;
        this.resolvedAt = resolvedAt;
        this.cancelledAt = cancelledAt;
        this.archivedAt = archivedAt;
    }
}
