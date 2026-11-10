package br.dev.gtech.servicedesk.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateSectorUseCase;
import br.dev.gtech.servicedesk.presentation.dto.CreateSectorDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sectors")
public class SectorController {
    private final CreateSectorUseCase createSectorUseCase;

    @PostMapping
    public ResponseEntity<UUID> handleCreateSector(
            @RequestBody @Valid CreateSectorDTO createSectorDTO) {
        UUID sectorId = createSectorUseCase.create(createSectorDTO.name());

        return ResponseEntity.status(HttpStatus.CREATED).body(sectorId);
    }
}
