package br.dev.gtech.servicedesk.presentation.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.infrastructure.mapper.SectorMapper;
import br.dev.gtech.servicedesk.infrastructure.repository.SectorRepository;
import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SectorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SectorRepository sectorRepository;

    @BeforeEach
    void setup() {
        sectorRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve retornar 200 ok e uma lista de setores")
    void shouldReturnAnSectorsList() throws Exception {
        Instant now = Instant.now();

        Sector sector1 = Sector.create(UUID.randomUUID(), "Financeiro",
                true, now, now);

        Sector sector2 = Sector.create(UUID.randomUUID(), "Contabilidade",
                false, now, now);

        sectorRepository.saveAll(List.of(
                SectorMapper.toEntity(sector1),
                SectorMapper.toEntity(sector2)));

        mockMvc.perform(get("/api/sectors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Deve retornar 404 NOT FOUND e uma lista vazia")
    void shouldReturnAnEmptyListWhenNoSectorExists() throws Exception {
        mockMvc.perform(get("/api/sectors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.length()").value(0));
    }
}