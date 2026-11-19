package br.dev.gtech.servicedesk.domain.model;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.enums.TicketPriority;
import br.dev.gtech.servicedesk.domain.enums.TicketStatus;

public class Ticket {
    private final UUID id;
    private final String title;
    private final String description;
    private final TicketStatus status;
    private final TicketPriority priority;
    private final UUID requesterId;
    private final UUID sectorId;
    private final UUID assigneeId;
    private final Instant createdAt;
    private final Instant firstStartedAt;
    private final Instant resolvedAt;
    private final Instant cancelledAt;
    private final Instant archivedAt;

    private Ticket(
            UUID id, String title, String description, TicketStatus status,
            TicketPriority priority, UUID requesterId, UUID sectorId, UUID assigneeId,
            Instant createdAt, Instant firstStartedAt, Instant resolvedAt,
            Instant cancelledAt, Instant archivedAt
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

    public static Ticket create(
            UUID id, String title, String description, TicketStatus status,
            TicketPriority priority, UUID requesterId, UUID sectorId, UUID assigneeId,
            Instant createdAt, Instant firstStartedAt, Instant resolvedAt,
            Instant cancelledAt, Instant archivedAt) {

        return new Ticket(
                id, title, description, status, priority, requesterId, sectorId, assigneeId,
                createdAt, firstStartedAt, resolvedAt, cancelledAt, archivedAt);
    }

    public Ticket withChangedPriority(TicketPriority ticketPriority) {
        return new Ticket(
                id, title, description, status, ticketPriority, requesterId, sectorId, assigneeId,
                createdAt, firstStartedAt, resolvedAt, cancelledAt, archivedAt);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public UUID getRequesterId() {
        return requesterId;
    }

    public UUID getSectorId() {
        return sectorId;
    }

    public UUID getAssigneeId() {
        return assigneeId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getFirstStartedAt() {
        return firstStartedAt;
    }

    public Instant getResolvedAt() {
        return resolvedAt;
    }

    public Instant getCancelledAt() {
        return cancelledAt;
    }

    public Instant getArchivedAt() {
        return archivedAt;
    }
}
