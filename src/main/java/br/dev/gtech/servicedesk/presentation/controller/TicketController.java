package br.dev.gtech.servicedesk.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.presentation.dto.CreateTicketDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final CreateTicketUseCase createTicketUseCase;

    @PostMapping
    public ResponseEntity<UUID> handleCreateTicket(@Valid  @RequestBody CreateTicketDTO createTicketDTO) {
        UUID ticketId = createTicketUseCase.execute(
                createTicketDTO.title(), createTicketDTO.description(), createTicketDTO.sectorId(),
                createTicketDTO.requesterId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(ticketId);
    }
}
