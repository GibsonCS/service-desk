package br.dev.gtech.servicedesk.application.exception;

public class InvalidTickedIdException extends RuntimeException {
    public InvalidTickedIdException(String message) {
        super(message);
    }
}
