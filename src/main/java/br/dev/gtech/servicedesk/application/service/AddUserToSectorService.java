package br.dev.gtech.servicedesk.application.service;

import java.util.Objects;
import java.util.UUID;

import br.dev.gtech.servicedesk.application.exception.DisabledSectorException;
import br.dev.gtech.servicedesk.application.exception.DisabledUserException;
import br.dev.gtech.servicedesk.application.exception.SectorNotFoundedException;
import br.dev.gtech.servicedesk.application.exception.UserAlreadyAssociatedToSectorException;
import br.dev.gtech.servicedesk.application.exception.UserNotFoundException;
import br.dev.gtech.servicedesk.application.port.in.AddUserToSectorUseCase;
import br.dev.gtech.servicedesk.application.port.out.SectorRepositoryPort;
import br.dev.gtech.servicedesk.application.port.out.UserRepositoryPort;
import br.dev.gtech.servicedesk.domain.model.Sector;
import br.dev.gtech.servicedesk.domain.model.User;

public class AddUserToSectorService implements AddUserToSectorUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final SectorRepositoryPort sectorRepositoryPort;

    public AddUserToSectorService(UserRepositoryPort userRepositoryPort, SectorRepositoryPort sectorRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.sectorRepositoryPort = sectorRepositoryPort;
    }

    @Override
    public void execute(UUID userId, UUID sectorId) {
        User foundedUser = userRepositoryPort.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Usuário não encontrado"));


        if (!foundedUser.getIsActive()) {
            throw new DisabledUserException(
                    "Não é possível adicionar um usuário inativo a um setor.");
        }

        Sector foundedSector = sectorRepositoryPort.findById(sectorId).orElseThrow(() ->
                new SectorNotFoundedException("Setor não encontrado"));

        if (!foundedSector.isActive()) {
            throw new DisabledSectorException(
                    "Não é possível adicionar um usuário a um setor inativo");
        }

        if (Objects.equals(sectorId, foundedUser.getSectorId())) {
            throw new UserAlreadyAssociatedToSectorException(
                    "Não é possível adicionar um usuário associado ao mesmo setor");
        }

        User userWithSectorId = foundedUser.withSectorId(foundedSector.getId());

        userRepositoryPort.save(userWithSectorId);
    }
}
