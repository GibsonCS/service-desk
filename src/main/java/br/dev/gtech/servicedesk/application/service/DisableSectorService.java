package br.dev.gtech.servicedesk.application.service;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.SectorAlreadyDisabledException;
import br.dev.gtech.servicedesk.application.exception.SectorNotFoundedException;
import br.dev.gtech.servicedesk.application.port.in.DisableSectorUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

public class DisableSectorService implements DisableSectorUseCase {

    private final SectorRepositoryPort sectorRepositoryPort;

    public DisableSectorService(SectorRepositoryPort sectorRepositoryPort) {
        this.sectorRepositoryPort = sectorRepositoryPort;
    }

    @Override
    public void execute(UUID sectorId) {
        Sector foundedSector = sectorRepositoryPort.findById(sectorId).orElseThrow(() ->
                new SectorNotFoundedException("Sector não encontrado")
        );

        if (!foundedSector.isActive()) {
            throw new SectorAlreadyDisabledException("Setor já inativo");
        }

        sectorRepositoryPort.save(foundedSector.disabled());
    }
}
