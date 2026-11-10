package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.SectorAlreadyExists;
import br.dev.gtech.servicedesk.application.port.in.CreateSectorUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;

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

        return null;
    }
}
