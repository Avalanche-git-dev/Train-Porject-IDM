package com.treno.application.dto;

public class UserGuest extends UserDTO {
	private String ruolo;
	private boolean permessi;
    // Costruttore e funzionalità specifiche per l'utente guest
    public UserGuest() {
        // Imposta eventuali valori di default, ad esempio nome o permessi limitati
        this.setNome("Guest");
        this.setRuolo("Guest");
        this.setPermessi(true);  // Un esempio di permesso
    }
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public boolean isPermessi() {
		return permessi;
	}
	public void setPermessi(boolean permessi) {
		this.permessi = permessi;
	}
    
}
