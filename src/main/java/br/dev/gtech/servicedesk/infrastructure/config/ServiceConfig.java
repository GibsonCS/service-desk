package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetUserUseCase;
import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateUserService;
import br.dev.gtech.servicedesk.application.service.GetUserService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserService(userRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public GetUserUseCase getUserUseCase() {
        return new GetUserService(userRepositoryPort);
    }
}
