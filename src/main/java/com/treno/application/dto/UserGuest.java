package com.treno.application.dto;

import com.treno.application.model.Ruolo;

public class UserGuest extends UserDTO {
	private Ruolo ruolo;
	private boolean permessi;
    // Costruttore e funzionalità specifiche per l'utente guest
    public UserGuest() {
        // Imposta eventuali valori di default, ad esempio nome o permessi limitati
        this.setNome("Guest");
        this.setRuolo(Ruolo.GUEST);
        this.setPermessi(true);  // Un esempio di permesso
    }
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public boolean isPermessi() {
		return permessi;
	}
	public void setPermessi(boolean permessi) {
		this.permessi = permessi;
	}
    
}
