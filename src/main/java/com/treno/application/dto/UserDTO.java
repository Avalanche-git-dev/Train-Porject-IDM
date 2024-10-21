package com.treno.application.dto;

import com.treno.application.model.Ruolo;

public class UserDTO {
	
	private long userId;
    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    private String telefono;
    private String stato;
    private double portafoglio;
    private String ruolo; // Aggiunta
	public UserDTO(long userId, String username, String password, String email, String nome, String cognome,
			String telefono, String stato, double portafoglio, String ruolo) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.stato = stato;
		this.portafoglio = portafoglio;
		this.ruolo = ruolo;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public double getPortafoglio() {
		return portafoglio;
	}
	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + ", stato=" + stato
				+ ", portafoglio=" + portafoglio + ", ruolo=" + ruolo + "]";
	}
    

    // Getters e Setters
	
	
}
