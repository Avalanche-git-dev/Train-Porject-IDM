package com.treno.application.model;

import com.treno.application.model.User.Stato;

import jakarta.persistence.*;

@Entity
@Table(name="admins")
public class Admin extends RegisteredUser {

	@Column(name="privilegio")
	private boolean privilegio;
	
	public Admin() {}
	
	public Admin(String username, String password, String email, String telefono,
			String nome, String cognome, boolean privilegio) {
		super(username, password, email, telefono, nome, cognome);
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

}