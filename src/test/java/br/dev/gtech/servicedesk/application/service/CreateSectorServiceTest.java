package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.SectorAlreadyExists;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateSectorServiceTest {
    private final UUID VALID_ID = UUID.randomUUID();
    private final String VALID_NAME = "Financeiro";
    private final boolean VALID_STATUS = true;
    private final Instant VALID_UPDATED_AT = Instant.now();
    private final Instant VALID_CREATED_AT = Instant.now();

    @Mock
    SectorRepositoryPort sectorRepositoryPort;

    @InjectMocks
    CreateSectorService createSectorService;

    @Test
    void shouldNotCreateSectorIfNameAlreadyExists() {
        Sector sector = Sector.create(VALID_ID, VALID_NAME, VALID_STATUS, VALID_UPDATED_AT,
                VALID_CREATED_AT);

        when(sectorRepositoryPort.findByName(VALID_NAME)).thenReturn(Optional.of(sector));

        assertThrows(SectorAlreadyExists.class, () ->
                createSectorService.create(VALID_NAME), "Setor já cadastrado");
    }
}