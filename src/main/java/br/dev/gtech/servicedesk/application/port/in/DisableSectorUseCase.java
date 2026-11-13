package br.dev.gtech.servicedesk.application.port.in;

import java.util.UUID;

public interface DisableSectorUseCase {
    void execute(UUID sectorId);
}
