package br.dev.gtech.servicedesk.application.port.in;

import java.util.HashSet;
import java.util.List;

import br.dev.gtech.servicedesk.domain.model.Sector;

public interface GetSectorsUseCase {
    List<Sector> get();
}
