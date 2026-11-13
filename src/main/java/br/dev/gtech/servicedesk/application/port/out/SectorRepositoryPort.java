package br.dev.gtech.servicedesk.application.port.out;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.Sector;

public interface SectorRepositoryPort {
    Optional<Sector> findByName(String sectorName);

    HashSet<Sector> findAll();

    UUID save(Sector sector);
}
