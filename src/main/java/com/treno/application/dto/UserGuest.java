package com.treno.application.dto;

import java.util.UUID;

public class UserGuest extends UserDTO {

    private String id;
    private String guest;

    // Costruttore
    public UserGuest() {
        // Genera un ID unico e assegna lo username "Guest"
        this.id = UUID.randomUUID().toString();  // Genera un ID random unico
        this.guest="Guest";
    }

    // Getter per l'ID
    public String getId() {
        return id;
    }

    // Getter per lo username

    // Setter opzionale per l'ID (se non vuoi permettere di modificarlo, puoi rimuoverlo)
    public void setId(String id) {
        this.id = id;
    }

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

    // Setter opzionale per lo username (puoi rimuoverlo se non vuoi permettere modifiche)
  

   
}
