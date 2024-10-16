package com.treno.eccezzioni;

public class TrenoCreazioneException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public TrenoCreazioneException(String message) {
        super(message);
    }
}