package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateSectorUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateSectorService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SectorServiceConfig {
    private final SectorRepositoryPort sectorRepositoryPort;

    @Bean
    public CreateSectorUseCase createSectorUseCase() {
        return new CreateSectorService(sectorRepositoryPort);
    }
}
