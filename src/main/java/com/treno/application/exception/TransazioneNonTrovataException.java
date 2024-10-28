package com.treno.application.exception;

public class TransazioneNonTrovataException extends TransazioneException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransazioneNonTrovataException(String message) {
        super(message);
    }
}
