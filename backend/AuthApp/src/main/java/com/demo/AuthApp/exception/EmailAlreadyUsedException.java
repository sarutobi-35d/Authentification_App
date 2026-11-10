package com.demo.AuthApp.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email){
        super("Un compte existe déjà avec l'email: " + email);
    }
}
