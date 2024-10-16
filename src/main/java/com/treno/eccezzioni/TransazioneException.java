package com.treno.eccezzioni;

public class TransazioneException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public TransazioneException(String message) {
        super(message);
    }
}