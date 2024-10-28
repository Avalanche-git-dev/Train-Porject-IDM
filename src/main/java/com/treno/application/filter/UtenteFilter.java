package com.treno.application.filter;

import com.treno.application.model.User.Stato;

public class UtenteFilter {
	
	
	//Filtri per utente
    
    private String cognome; // servono effettivamente ?
    private String email;
    private String nome;
    private String username;
    private String password;
	private Stato stato;
    private Integer numeroTreni;
    private Integer numeroValutazioni;
    private Integer numeroTransazioni;
    private double portafoglio;
	public UtenteFilter() {
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	public int getNumeroTreni() {
		return numeroTreni;
	}
	public void setNumeroTreni(int numeroTreni) {
		this.numeroTreni = numeroTreni;
	}
	public int getNumeroValutazioni() {
		return numeroValutazioni;
	}
	public void setNumeroValutazioni(int numeroValutazioni) {
		this.numeroValutazioni = numeroValutazioni;
	}
	public int getNumeroTransazioni() {
		return numeroTransazioni;
	}
	public void setNumeroTransazioni(int numeroTransazioni) {
		this.numeroTransazioni = numeroTransazioni;
	}
	public double getPortafoglio() {
		return portafoglio;
	}
	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}
    
}
