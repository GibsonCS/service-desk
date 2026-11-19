package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateTicketPriority;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateTicketService;
import br.dev.gtech.servicedesk.application.service.UpdateTicketPriorityService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TicketServiceConfig {
    private final TickedRepositoryPort tickedRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final SectorRepositoryPort sectorRepositoryPort;

    @Bean
    public CreateTicketUseCase createTicketUseCase() {
        return new CreateTicketService(tickedRepositoryPort, userRepositoryPort, sectorRepositoryPort);
    }

    @Bean
    public UpdateTicketPriority updateTicketPriority() {
        return new UpdateTicketPriorityService(tickedRepositoryPort);
    }
}
