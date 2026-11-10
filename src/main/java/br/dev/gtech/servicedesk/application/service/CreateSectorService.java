package br.dev.gtech.servicedesk.application.service;

import java.time.Instant;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.SectorAlreadyExists;
import br.dev.gtech.servicedesk.application.port.in.CreateSectorUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

public class CreateSectorService implements CreateSectorUseCase {

    private final SectorRepositoryPort sectorRepository;

    public CreateSectorService(SectorRepositoryPort sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public UUID create(String sectorName) {
        sectorRepository.findByName(sectorName).ifPresent(s -> {
            throw new SectorAlreadyExists("Setor já cadastrado");
        });

        Instant now = Instant.now();

        Sector createdSector = Sector.create(UUID.randomUUID(),
                sectorName, true, now, now);

                sectorRepository.save(createdSector);
        return createdSector.getId();
    }
}
