package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetTicketUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateTicketPriorityUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.TicketRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateTicketService;
import br.dev.gtech.servicedesk.application.service.GetTicketService;
import br.dev.gtech.servicedesk.application.service.UpdateTicketPriorityUseCaseService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TicketServiceConfig {
    private final TicketRepositoryPort ticketRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final SectorRepositoryPort sectorRepositoryPort;

    @Bean
    public CreateTicketUseCase createTicketUseCase() {
        return new CreateTicketService(ticketRepositoryPort, userRepositoryPort, sectorRepositoryPort);
    }

    @Bean
    public UpdateTicketPriorityUseCase updateTicketPriority() {
        return new UpdateTicketPriorityUseCaseService(ticketRepositoryPort);
    }

    @Bean
    public GetTicketUseCase getTicketUseCase() {
        return new GetTicketService(ticketRepositoryPort);
    }
}
