package br.dev.gtech.servicedesk.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import br.dev.gtech.servicedesk.infrastructure.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
}
