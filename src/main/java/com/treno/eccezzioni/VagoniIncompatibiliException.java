package com.treno.eccezzioni;
public class VagoniIncompatibiliException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VagoniIncompatibiliException(String message) {
        super(message);
    }
}

