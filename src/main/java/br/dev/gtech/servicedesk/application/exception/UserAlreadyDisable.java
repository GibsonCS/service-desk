package br.dev.gtech.servicedesk.application.exception;

public class UserAlreadyDisable extends RuntimeException {
    public UserAlreadyDisable(String message) {
        super(message);
    }
}
