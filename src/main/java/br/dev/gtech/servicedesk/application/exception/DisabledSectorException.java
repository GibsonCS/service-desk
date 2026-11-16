package br.dev.gtech.servicedesk.application.exception;

public class DisabledSectorException extends RuntimeException {
    public DisabledSectorException(String message) {
        super(message);
    }
}
