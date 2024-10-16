package com.treno.eccezzioni;
public class TroppiRistorantiException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TroppiRistorantiException(String message) {
        super(message);
    }
}
