package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.application.port.out.TickedRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateTicketService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TicketServiceConfig {
    private final TickedRepositoryPort tickedRepositoryPort;

    @Bean
    public CreateTicketUseCase createTicketUseCase() {
        return new CreateTicketService(tickedRepositoryPort);
    }
}
