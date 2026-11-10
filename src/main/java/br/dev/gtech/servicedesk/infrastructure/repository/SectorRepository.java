package br.dev.gtech.servicedesk.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.infrastructure.entity.SectorEntity;

public interface SectorRepository extends JpaRepository<SectorEntity, UUID> {
    Optional<SectorEntity> findByName(String sectorName);
}
