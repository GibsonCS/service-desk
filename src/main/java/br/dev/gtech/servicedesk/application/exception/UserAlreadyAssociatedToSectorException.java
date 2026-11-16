package br.dev.gtech.servicedesk.application.exception;

public class UserAlreadyAssociatedToSectorException extends RuntimeException {
    public UserAlreadyAssociatedToSectorException(String message) {
        super(message);
    }
}
