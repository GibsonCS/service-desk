package br.dev.gtech.servicedesk.application.exception;

public class InvalidRequesterIdException extends RuntimeException {
    public InvalidRequesterIdException(String message) {
        super(message);
    }
}
