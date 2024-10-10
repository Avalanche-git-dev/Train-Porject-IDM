package com.treno.application.model;

import java.util.LinkedList;
import java.util.List;

import com.treno.application.model.Treno.Valutazione;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends RegisteredUser {

	@Column(name = "stato")
	private Stato stato;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Treno> l = new LinkedList <Treno>();

	@OneToMany(mappedBy = "acquirente", fetch = FetchType.LAZY)
	private List<Transazione> transazioneAcquisto = new LinkedList<Transazione>();

	@OneToMany(mappedBy = "venditore", fetch = FetchType.LAZY)
	private List<Transazione> transazioneVendita = new LinkedList<Transazione>();

	@Column(name = "portafoglio")
	private double portafoglio;

	public List<Transazione> getTransazioneAcquisto() {
		return transazioneAcquisto;
	}

	public void setTransazioneAcquisto(List<Transazione> transazioneAcquisto) {
		this.transazioneAcquisto = transazioneAcquisto;
	}

	public List<Transazione> getTransazioneVendita() {
		return transazioneVendita;
	}

	public void setTransazioneVendita(List<Transazione> transazioneVendita) {
		this.transazioneVendita = transazioneVendita;
	}

	public User() {
	}

	public User(String username, String password, String email, String telefono, String nome, String cognome,
			double portafoglio) {
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
