package br.dev.gtech.servicedesk.application.exception;

public class InvalidTicketIdException extends RuntimeException {
    public InvalidTicketIdException(String message) {
        super(message);
    }
}
