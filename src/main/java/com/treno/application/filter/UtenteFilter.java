package com.treno.application.filter;

import com.treno.application.model.User.Stato;

public class UtenteFilter {
	
	
	//Filtri per utente
    private String nome;
    private String cognome; // servono effettivamente ?
    private String email;
    private Integer età;
    
    
    //Filtri per admin
    private String username;
    private String password;
    private Stato stato;
    private int numeroTreni;
    private int numeroValutazioni;
    private int numeroTransazioni;
   // private int ammontareTotaleTransazioni;
	public UtenteFilter(String nome, String cognome, String email, Integer età, String username, String password,
			Stato stato, int numeroTreni, int numeroValutazioni, int numeroTransazioni) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.età = età;
		this.username = username;
		this.password = password;
		this.stato = stato;
		this.numeroTreni = numeroTreni;
		this.numeroValutazioni = numeroValutazioni;
		this.numeroTransazioni = numeroTransazioni;
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
	@Override
	public String toString() {
		return "UtenteFilter [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", età=" + età
				+ ", username=" + username + ", password=" + password + ", stato=" + stato + ", numeroTreni="
				+ numeroTreni + ", numeroValutazioni=" + numeroValutazioni + ", numeroTransazioni=" + numeroTransazioni
				+ "]";
	}

    
    
}