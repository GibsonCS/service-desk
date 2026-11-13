package br.dev.gtech.servicedesk.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.Sector;

public interface SectorRepositoryPort {
    Optional<Sector> findByName(String sectorName);

    List<Sector> findAll();

    UUID save(Sector sector);

    Optional<Sector> findById(UUID sectorId);
}
