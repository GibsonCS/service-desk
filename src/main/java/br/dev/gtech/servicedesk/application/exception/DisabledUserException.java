package br.dev.gtech.servicedesk.application.exception;

public class DisabledUserException extends RuntimeException {
    public DisabledUserException(String message) {
        super(message);
    }
}
