package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.SectorAlreadyDisabledException;
import br.dev.gtech.servicedesk.application.exception.SectorNotFoundedException;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisableSectorServiceTest {
    private final UUID VALID_ID = UUID.randomUUID();
    private final String VALID_NAME = "Financeiro";
    private final boolean VALID_STATUS = true;
    private final Instant VALID_UPDATED_AT = Instant.now();
    private final Instant VALID_CREATED_AT = Instant.now();

    @Mock
    SectorRepositoryPort sectorRepositoryPort;

    @InjectMocks
    DisableSectorService disableSectorService;


    @Test
    @DisplayName("Deve desabilitar setor inexistente")
    void shouldDisableSectorNotFound() {
        when(sectorRepositoryPort.findById(VALID_ID)).thenReturn(Optional.empty());

        assertThrows(SectorNotFoundedException.class, () ->
                disableSectorService.execute(VALID_ID), "Setor não encontrado");

        verify(sectorRepositoryPort, times(1)).findById(VALID_ID);
    }

    @Test
    @DisplayName("Não deve desabilitar setor já inativo")
    void shouldNotDisableSectorAlreadyDisabled() {

        Sector sector = Sector.create(VALID_ID, VALID_NAME, false, VALID_UPDATED_AT,
                VALID_CREATED_AT);

        when(sectorRepositoryPort.findById(VALID_ID)).thenReturn(Optional.of(sector));

        assertThrows(SectorAlreadyDisabledException.class, () ->
                disableSectorService.execute(VALID_ID), "Setor já inativo");

        verify(sectorRepositoryPort, times(1)).findById(VALID_ID);
    }

    @Test
    @DisplayName("Deve desabilitar um setor")
    void shouldDisableSector() {

        Sector sector = Sector.create(VALID_ID, VALID_NAME, VALID_STATUS, VALID_UPDATED_AT,
                VALID_CREATED_AT);

        when(sectorRepositoryPort.findById(VALID_ID)).thenReturn(Optional.of(sector));

        disableSectorService.execute(VALID_ID);

        ArgumentCaptor<Sector> captor = ArgumentCaptor.forClass(Sector.class);

        verify(sectorRepositoryPort).save(captor.capture());

        Sector savedSector = captor.getValue();

        assertEquals(false,savedSector.isActive());

        verify(sectorRepositoryPort, times(1)).findById(VALID_ID);
    }
}