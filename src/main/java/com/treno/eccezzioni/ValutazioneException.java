package com.treno.eccezzioni;

public class ValutazioneException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public ValutazioneException(String message) {
        super(message);
    }
}