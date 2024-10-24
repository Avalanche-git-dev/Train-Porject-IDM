 package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="admins")
public class Admin extends User  {
	
	
	@Column(name =  "privilegio" , nullable = false)
	public boolean privilegio;

	
	public Admin() {
	}
	
	
	
	
	public Admin(boolean privilegio) {
		super();
		this.privilegio = privilegio;
	}




	public void controllaAttivita(User user) {
		
	}
	
	public void banna(User user) {
		user.setStato(Stato.locked);
	}
	
	public void riattiva(User user) {
		user.setStato(Stato.unlocked);
	}

	public boolean isPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}

} 