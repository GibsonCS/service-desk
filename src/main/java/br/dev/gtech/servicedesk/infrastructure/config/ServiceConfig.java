package br.dev.gtech.servicedesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateEmailUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateNameUseCase;
import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.application.service.CreateUserService;
import br.dev.gtech.servicedesk.application.service.GetUserService;
import br.dev.gtech.servicedesk.application.service.UpdateEmail;
import br.dev.gtech.servicedesk.application.service.UpdateName;
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

    @Bean
    public UpdateNameUseCase updateNameUseCase() {
        return new UpdateName(userRepositoryPort);
    }

    @Bean
    public UpdateEmailUseCase updateEmailUseCase() {
        return new UpdateEmail(userRepositoryPort);
    }
}
