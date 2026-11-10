package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

public interface CreateSectorUseCase {
    UUID create(String sectorName);
}
