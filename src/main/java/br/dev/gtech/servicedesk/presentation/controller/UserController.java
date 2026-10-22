package br.dev.gtech.servicedesk.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetUserUseCase;
import br.dev.gtech.servicedesk.presentation.dto.CreateUserDTO;
import br.dev.gtech.servicedesk.presentation.dto.GetUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final CreateUserUseCase createUserService;
    private final GetUserUseCase getUserUseCase;

    @PostMapping("/users")
    public ResponseEntity<UUID> handleCreateUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        UUID createdUserId = createUserService.create(
                createUserDTO.name(),
                createUserDTO.email(),
                createUserDTO.password());

        return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserDTO> handleGetUser(@Valid @PathVariable UUID id) {
        GetUserDTO getUserDTO = GetUserDTO.toDTO(getUserUseCase.get(id));
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }
}
