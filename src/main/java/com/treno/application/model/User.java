package com.treno.application.model;

import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true ,fetch = FetchType.LAZY)
	private List<Treno> listaTreni;

	@OneToMany(mappedBy = "acquirente", fetch = FetchType.LAZY)
	private List<Transazione> transazioniAcquisto;

	@OneToMany(mappedBy = "venditore", fetch = FetchType.LAZY)
	private List<Transazione> transazioniVendita;
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<Valutazione> valutazioni;

	@Column(name = "portafoglio")
	private double portafoglio;
	
	//Costruttore

	public User() {
		this.stato = Stato.unlocked;
		this.listaTreni = new ArrayList<Treno>();
		this.transazioniAcquisto = new ArrayList<Transazione>();
		this.transazioniVendita = new ArrayList<Transazione>();
		this.valutazioni= new ArrayList<Valutazione>();
	}
	
	//Lista Treni
	
	public List<Treno> getListaTreni() {
		return listaTreni;
	}
	
	public void setListaTreni(List<Treno> listaTreni) {
		this.listaTreni = listaTreni;
	}
	
	public void addTreno (Treno treno) {
		this.listaTreni.add(treno);
		treno.setOwner(this);
	}
	
	public void removeTreno (Treno treno) {
		this.listaTreni.remove(treno);
		treno.setOwner(null);
	}
	
	//Transazioni Acquisto
	
	public List<Transazione> getTransazioniAcquisto() {
		return transazioniAcquisto;
	}

	public void setTransazioniAcquisto(List<Transazione> transazioniAcquisto) {
		this.transazioniAcquisto = transazioniAcquisto;
	}
	
	public void addTransazioneAcquisto(Transazione transazione) {
		this.transazioniAcquisto.add(transazione);
		transazione.setAcquirente(this);
	}

	public void removeTransazioneAcquisto(Transazione transazione) {
		this.transazioniAcquisto.remove(transazione);
		transazione.setAcquirente(null);
	}
	
	//Transazioni Vendita
	
	public List<Transazione> getTransazioniVendita() {
		return transazioniVendita;
	}

	public void setTransazioniVendita(List<Transazione> transazioniVendita) {
		this.transazioniVendita = transazioniVendita;
	}
	
	public void addTransazioneVendita(Transazione transazione) {
		this.transazioniVendita.add(transazione);
		transazione.setVenditore(this);
	}

	public void removeTransazioneVendita(Transazione transazione) {
		this.transazioniVendita.remove(transazione);
		transazione.setVenditore(null);
	}
	
	//Valutazioni
	
		public List<Valutazione> getValutazioni() {
			return valutazioni;
		}

		public void setValutazioni(List<Valutazione> valutazioni) {
			this.valutazioni = valutazioni;
		}
		
		public final void addValutazione(Valutazione valutazione) {
			valutazioni.add(valutazione);
			valutazione.setUser(this);
		}
		
		public final void removeValutazione(Valutazione valutazione) {
			valutazioni.remove(valutazione);
			valutazione.setUser(null);
		}
	
    //Stato
	
	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
	
    //Portafoglio
	
	public double getPortafoglio() {
		return portafoglio;
	}

	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}

    //Enum Stato

	public enum Stato {
		locked, unlocked
	}

	@Override
	public String toString() {
		return "User [stato=" + stato + ", listaTreni=" + listaTreni + ", transazioniAcquisto=" + transazioniAcquisto
				+ ", transazioniVendita=" + transazioniVendita + ", valutazioni=" + valutazioni + ", portafoglio="
				+ portafoglio + ", = " + super.toString() + "]";
	}

	
	
	

}
