package br.dev.gtech.servicedesk.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.infrastructure.entity.SectorEntity;
import br.dev.gtech.servicedesk.infrastructure.mapper.SectorMapper;
import br.dev.gtech.servicedesk.infrastructure.repository.SectorRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SectorRepositoryAdapter implements SectorRepositoryPort {
    private final SectorRepository sectorRepository;

    @Override
    public Optional<Sector> findByName(String sectorName) {
        Optional<SectorEntity> foundedSectorEntity = sectorRepository.findByName(sectorName);

        if (foundedSectorEntity.isEmpty()) {
            return Optional.empty();
        }

        Sector domainSector = SectorMapper.toDomain(foundedSectorEntity.get());

        return Optional.of(domainSector);
    }

    @Override
    public UUID save(Sector sector) {
        sectorRepository.save(SectorMapper.toEntity(sector));
        return sector.getId();
    }
}
