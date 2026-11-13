package br.dev.gtech.servicedesk.application.port.in;

import java.util.HashSet;

import br.dev.gtech.servicedesk.domain.model.Sector;

public interface GetSectorsUseCase {
    HashSet<Sector> get();
}
