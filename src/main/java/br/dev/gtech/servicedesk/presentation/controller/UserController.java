package br.dev.gtech.servicedesk.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.presentation.dto.CreateUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final CreateUserUseCase createUserService;

    @PostMapping("/users")
    public ResponseEntity<UUID> handleCreateUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        UUID createdUserId = createUserService.create(
                createUserDTO.name(),
                createUserDTO.email(),
                createUserDTO.password());

        return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
    }
}
