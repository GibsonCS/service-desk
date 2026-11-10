package br.dev.gtech.servicedesk.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.DisableUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetUserUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateEmailUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateNameUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdatePasswordUseCase;
import br.dev.gtech.servicedesk.presentation.dto.CreateUserDTO;
import br.dev.gtech.servicedesk.presentation.dto.GetUserDTO;
import br.dev.gtech.servicedesk.presentation.dto.UpdateEmailDTO;
import br.dev.gtech.servicedesk.presentation.dto.UpdateNameDTO;
import br.dev.gtech.servicedesk.presentation.dto.UpdatePasswordDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserService;
    private final GetUserUseCase getUserUseCase;
    private final UpdateNameUseCase updateNameUseCase;
    private final UpdateEmailUseCase updateEmailUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final DisableUserUseCase disableUserUseCase;

    @PostMapping
    public ResponseEntity<UUID> handleCreateUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        UUID createdUserId = createUserService.create(
                createUserDTO.name(),
                createUserDTO.email(),
                createUserDTO.password());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> handleGetUser(@PathVariable UUID id) {
        GetUserDTO getUserDTO = GetUserDTO.toDTO(getUserUseCase.get(id));
        return ResponseEntity.ok(getUserDTO);
    }

    @PatchMapping("/name/{id}")
    public ResponseEntity<Void> handleUpdateName(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateNameDTO updateNameDTO
    ) {
        updateNameUseCase.update(id, updateNameDTO.name());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/email/{id}")
    public ResponseEntity<Void> handleUpdateEmail(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEmailDTO updateEmailDTO
    ) {
        updateEmailUseCase.update(id, updateEmailDTO.email());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<Void> handleUpdatePassword(@PathVariable UUID id,
                                                     @Valid @RequestBody UpdatePasswordDTO updatePasswordDTO
    ) {
        updatePasswordUseCase.update(id,
                updatePasswordDTO.password(),
                updatePasswordDTO.confirmPassword()
        );

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> handleDisableUser(@PathVariable UUID id) {
        disableUserUseCase.disable(id);

        return ResponseEntity.noContent().build();
    }
}
