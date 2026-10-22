package br.dev.gtech.servicedesk.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.dev.gtech.servicedesk.application.port.out.PasswordEncoderPort;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
