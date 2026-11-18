package br.dev.gtech.servicedesk.application.exception;

public class TicketPriorityException extends RuntimeException {
    public TicketPriorityException(String message) {
        super(message);
    }
}
