package com.treno.application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="registeredUsers") // Così o col trattino basso?
@Inheritance(strategy = InheritanceType.JOINED)
public class RegisteredUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name="username")
	private String username;
	
	@Pattern(regexp = "[a-zA-Z0-9_.]*")
	@Column(name="password")
	private String password;
	
	@Pattern(regexp = "")
	@Column(name="email")
	private String email;
	
	@Pattern(regexp = "")
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	public RegisteredUser() {}
	
	public RegisteredUser(String username, String password, String email, String telefono, String nome, 
			String cognome) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
		this.nome = nome;
		this.cognome = cognome;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	
}