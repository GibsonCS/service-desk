package br.dev.gtech.servicedesk.application.exception;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(String message) {
        super(message);
    }
}
