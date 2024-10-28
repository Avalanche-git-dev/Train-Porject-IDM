package com.treno.application.dto;

import java.util.UUID;

public class UserGuest extends UserDTO {

    private UUID id ;
    private String guest ;

    // Costruttore
    public UserGuest() {
        this.id = UUID.randomUUID(); 
        this.guest="Guest";
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getGuest() {
		return guest;
	}


	public void setGuest(String guest) {
		this.guest = guest;



   
		
	}
}
