package br.dev.gtech.servicedesk.application.port.out;

import java.util.Optional;

import br.dev.gtech.servicedesk.domain.model.Sector;

public interface SectorRepositoryPort {
    Optional<Sector> findByName(String sectorName);
}
