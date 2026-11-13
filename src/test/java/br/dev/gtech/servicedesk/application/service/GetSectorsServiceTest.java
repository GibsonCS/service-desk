package br.dev.gtech.servicedesk.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetSectorsServiceTest {
    UUID VALID_ID = UUID.randomUUID();
    String VALID_NAME = "Financeiro";
    boolean IS_ACTIVE = true;
    Instant CREATED_AT = Instant.now();
    Instant UPDATED_AT = Instant.now();

    Sector sector;
    List<Sector> sectors;

    @Mock
    SectorRepositoryPort sectorRepositoryPort;
    @InjectMocks
    GetSectorsService getSectorsService;

    @BeforeEach
    void setup() {
        sectors = new ArrayList<>();
        sector = Sector.create(VALID_ID, VALID_NAME, IS_ACTIVE, CREATED_AT, UPDATED_AT);
    }

    @Test
    void shouldReturnAnEmptyList() {
        when(sectorRepositoryPort.findAll()).thenReturn(sectors);

        List<Sector> result = getSectorsService.get();

        assertTrue(result.isEmpty());

        verify(sectorRepositoryPort, times(1)).findAll();
    }

    @Test
    void shouldReturnASectorsList() {
        sectors.add(sector);

        when(sectorRepositoryPort.findAll()).thenReturn(sectors);

        List<Sector> result = getSectorsService.get();

        assertTrue(result.contains(sector));

        verify(sectorRepositoryPort, times(1)).findAll();
    }
}