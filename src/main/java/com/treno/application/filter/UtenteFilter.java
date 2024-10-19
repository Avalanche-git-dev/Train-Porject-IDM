package com.treno.application.filter;

import com.treno.application.model.User.Stato;

public class UtenteFilter {
	
	
	//Filtri per utente
    private String nome;
    private String cognome;
    private String email;
    private Integer età;
    
    
    //Filtri per admin
    private String username;
    private String password;
    private Stato stato;
    
    private String telefono;
    private String nuovaPassword;
    private int numeroTreni;
    private int numeroValutazioni;
    private int numeroTransazioni;
    private int ammontareTotaleTransazioni;
	public UtenteFilter(String nome, String cognome, String email, Integer età, String username, String password,
			Stato stato, String telefono, String nuovaPassword, int numeroTreni, int numeroValutazioni,
			int numeroTransazioni, int ammontareTotaleTransazioni) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.età = età;
		this.username = username;
		this.password = password;
		this.stato = stato;
		this.telefono = telefono;
		this.nuovaPassword = nuovaPassword;
		this.numeroTreni = numeroTreni;
		this.numeroValutazioni = numeroValutazioni;
		this.numeroTransazioni = numeroTransazioni;
		this.ammontareTotaleTransazioni = ammontareTotaleTransazioni;
	}
	public UtenteFilter() {
		super();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Integer getEtà() {
		return età;
	}
	public void setEtà(Integer età) {
		this.età = età;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNuovaPassword() {
		return nuovaPassword;
	}
	public void setNuovaPassword(String nuovaPassword) {
		this.nuovaPassword = nuovaPassword;
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
	public int getAmmontareTotaleTransazioni() {
		return ammontareTotaleTransazioni;
	}
	public void setAmmontareTotaleTransazioni(int ammontareTotaleTransazioni) {
		this.ammontareTotaleTransazioni = ammontareTotaleTransazioni;
	}
    
    
    
}
