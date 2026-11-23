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

import br.dev.gtech.servicedesk.application.port.in.CreateTicketUseCase;
import br.dev.gtech.servicedesk.application.port.in.GetTicketUseCase;
import br.dev.gtech.servicedesk.application.port.in.UpdateTicketPriorityUseCase;
import br.dev.gtech.servicedesk.domain.model.Ticket;
import br.dev.gtech.servicedesk.presentation.dto.CreateTicketDTO;
import br.dev.gtech.servicedesk.presentation.dto.GetTicketDTO;
import br.dev.gtech.servicedesk.presentation.dto.UpdateTicketPriorityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final CreateTicketUseCase createTicketUseCase;
    private final UpdateTicketPriorityUseCase updateTicketPriorityUseCase;
    private final GetTicketUseCase getTicketUseCase;

    @PostMapping
    public ResponseEntity<UUID> handleCreateTicket(@Valid @RequestBody CreateTicketDTO createTicketDTO) {
        UUID ticketId = createTicketUseCase.execute(
                createTicketDTO.title(), createTicketDTO.description(), createTicketDTO.sectorId(),
                createTicketDTO.requesterId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ticketId);
    }

    @PatchMapping("/{ticketId}/priority")
    public ResponseEntity<Void> handleUpdateTicketPriority(
            @PathVariable UUID ticketId,
            @Valid @RequestBody UpdateTicketPriorityDTO updateTicketPriorityDTO) {

        updateTicketPriorityUseCase.execute(
                ticketId, updateTicketPriorityDTO.ticketPriority());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<GetTicketDTO> handleGetTicket(@PathVariable UUID ticketId) {
        Ticket ticket = getTicketUseCase.execute(ticketId);

        GetTicketDTO getTicketDTO = GetTicketDTO.toDTO(ticket);

        return ResponseEntity.ok(getTicketDTO);
    }
}
