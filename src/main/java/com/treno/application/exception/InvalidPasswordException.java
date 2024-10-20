package com.treno.application.exception;

public class InvalidPasswordException extends InvalidCredentialsException {
    private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message) {
        super(message);
    }
}