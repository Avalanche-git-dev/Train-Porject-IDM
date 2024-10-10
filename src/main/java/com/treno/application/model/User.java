package com.treno.application.model;

import java.util.List;

import com.treno.application.model.Treno.Valutazione;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User extends RegisteredUser {

	@Column(name="stato")
	private Stato stato;
	
	@OneToMany
	private List<Treno> l;
	
	@Column(name="portafoglio")
	private double portafoglio;
	
	public User() {}
	
	public User(String username, String password, String email, String telefono, 
			String nome, String cognome, double portafoglio) {
		super(username, password, email, telefono, nome, cognome);
		this.stato = Stato.unlocked;
		this.portafoglio = portafoglio;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public List<Treno> getL() {
		return l;
	}

	public void setL(List<Treno> l) {
		this.l = l;
	}

	public double getPortafoglio() {
		return portafoglio;
	}

	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}
	
	public void valutaTreno(Treno treno, Valutazione valutazione) {
		treno.addValutazione(valutazione);
	}
	
	@Override
	public String toString() {
		return "User [stato=" + stato + ", l=" + l + ", portafoglio=" + portafoglio + "]";
	}
	
	public enum Stato {
		locked, unlocked
	}
	
}
