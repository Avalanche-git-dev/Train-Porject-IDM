package com.treno.application.exception;

public class InvalidPhoneNumberException extends InvalidCredentialsException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPhoneNumberException(String message) {
        super(message);
    }
}


