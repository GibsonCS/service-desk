package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateSectorUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetSectorsUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateSectorService;
import br.dev.gtech.servicedesk.application.service.GetSectorsService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SectorServiceConfig {
    private final SectorRepositoryPort sectorRepositoryPort;

    @Bean
    public CreateSectorUseCase createSectorUseCase() {
        return new CreateSectorService(sectorRepositoryPort);
    }

    @Bean
    public GetSectorsUseCase getSectorsUseCase() {
        return new GetSectorsService(sectorRepositoryPort);
    }
}
