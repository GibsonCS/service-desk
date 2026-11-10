package br.dev.gtech.servicedesk.application.exception;

public class SectorAlreadyExists extends RuntimeException {
    public SectorAlreadyExists(String message) {
        super(message);
    }
}
