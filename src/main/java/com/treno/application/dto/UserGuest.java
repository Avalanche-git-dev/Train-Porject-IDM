package com.treno.application.dto;

import java.util.UUID;

public class UserGuest extends UserDTO {

    private String id;
    private String guest;

    // Costruttore
    public UserGuest() {
        this.id = UUID.randomUUID().toString(); 
        this.guest="Guest";
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;



   
		
	}
}
