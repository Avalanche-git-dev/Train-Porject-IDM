package com.treno.eccezzioni;

public class FondiInsufficientiException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FondiInsufficientiException(String message) {
        super(message);
    }
}