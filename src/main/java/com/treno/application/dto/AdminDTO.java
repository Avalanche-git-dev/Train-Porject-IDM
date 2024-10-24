package com.treno.application.dto;

import com.treno.application.model.User.Stato;

public class AdminDTO extends UserDTO {
	
	public boolean privilegio;

	public boolean isPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}

	public AdminDTO(boolean privilegio) {
		super();
		this.privilegio = privilegio;
	}

	public AdminDTO() {
		super();
	}

	public AdminDTO(long userId, String username, String password, String email, String nome, String cognome,
			String telefono, Stato stato, double portafoglio,boolean priviliegio) {
		super(userId, username, password, email, nome, cognome, telefono, stato, portafoglio);
		this.privilegio=priviliegio;
	}
	
	
	
	

}
