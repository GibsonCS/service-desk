package br.dev.gtech.servicedesk.infrastructure.mapper;

import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.infrastructure.entity.SectorEntity;

public class SectorMapper {

    public static Sector toDomain(SectorEntity sectorEntity) {
        return Sector.create(sectorEntity.getId(), sectorEntity.getName(), sectorEntity.isActive(),
                sectorEntity.getUpdatedAt(), sectorEntity.getCreatedAt());
    }

    public static SectorEntity toEntity(Sector sector) {
        SectorEntity sectorEntity = new SectorEntity();

        sectorEntity.setId(sector.getId());
        sectorEntity.setName(sector.getName());
        sectorEntity.setActive(sector.isActive());
        sectorEntity.setUpdatedAt(sector.getUpdatedAt());
        sectorEntity.setCreatedAt(sector.getCreatedAt());

        return sectorEntity;
    }
}
