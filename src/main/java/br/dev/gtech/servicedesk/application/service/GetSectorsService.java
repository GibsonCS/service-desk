package br.dev.gtech.servicedesk.application.service;

import java.util.HashSet;

import br.dev.gtech.servicedesk.application.port.in.GetSectorsUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

public class GetSectorsService implements GetSectorsUseCase {
    private final SectorRepositoryPort sectorRepositoryPort;

    public GetSectorsService(SectorRepositoryPort sectorRepositoryPort) {
        this.sectorRepositoryPort = sectorRepositoryPort;
    }

    @Override
    public HashSet<Sector> get() {
        HashSet<Sector> foundedSectors = sectorRepositoryPort.findAll();

        if (foundedSectors.isEmpty()) {
            return new HashSet<>();
        }

        return foundedSectors;
    }
}
