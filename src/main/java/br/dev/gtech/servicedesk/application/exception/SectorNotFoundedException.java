package br.dev.gtech.servicedesk.application.exception;

public class SectorNotFoundedException extends RuntimeException {
    public SectorNotFoundedException(String message) {
        super(message);
    }
}
