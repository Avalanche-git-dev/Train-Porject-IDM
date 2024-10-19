package com.treno.application.exception;

public class SessionExpiredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public SessionExpiredException(String message) {
        super(message);
    }
}