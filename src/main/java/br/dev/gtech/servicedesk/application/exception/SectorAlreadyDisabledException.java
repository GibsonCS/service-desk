package br.dev.gtech.servicedesk.application.exception;

public class SectorAlreadyDisabledException extends RuntimeException {
    public SectorAlreadyDisabledException(String message) {
        super(message);
    }
}
