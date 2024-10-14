package com.treno.application.model;

import com.treno.application.model.User.Stato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="admins")
public class Admin extends RegisteredUser {

	@Column(name="privilegio")
	private boolean privilegio;
	
	public Admin() {}
	
	public void controllaAttivita(User user) {
		
	}
	
	public void banna(User user) {
		user.setStato(Stato.locked);
	}
	
	public void riattiva(User user) {
		user.setStato(Stato.unlocked);
	}

}