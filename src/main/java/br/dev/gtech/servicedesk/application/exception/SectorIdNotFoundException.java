package br.dev.gtech.servicedesk.application.exception;

public class SectorIdNotFoundException extends RuntimeException {
    public SectorIdNotFoundException(String message) {
        super(message);
    }
}
