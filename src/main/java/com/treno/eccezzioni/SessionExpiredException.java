package com.treno.eccezzioni;

public class SessionExpiredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public SessionExpiredException(String message) {
        super(message);
    }
}