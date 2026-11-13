package br.dev.gtech.servicedesk.application.service;

import java.util.ArrayList;
import java.util.List;

import br.dev.gtech.servicedesk.application.port.in.GetSectorsUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

public class GetSectorsService implements GetSectorsUseCase {
    private final SectorRepositoryPort sectorRepositoryPort;

    public GetSectorsService(SectorRepositoryPort sectorRepositoryPort) {
        this.sectorRepositoryPort = sectorRepositoryPort;
    }

    @Override
    public List<Sector> get() {
        List<Sector> foundedSectors = sectorRepositoryPort.findAll();

        if (foundedSectors.isEmpty()) {
            return new ArrayList<>();
        }

        return foundedSectors;
    }
}
