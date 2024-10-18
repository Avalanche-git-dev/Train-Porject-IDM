package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "registeredUsers") // Così o col trattino basso?
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RegisteredUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@Column(name = "username", nullable = false , unique = true)
	private String username;

	// Almeno 8 caratteri: maiuscola, minuscola, numero e carattere speciale
	// @Pattern(regexp =
	// "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$\r\n")
	@Column(name = "password")
	private String password;

	// @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$\r\n")
	@Column(name = "email")
	private String email;

	// @Pattern(regexp =
	// "^\\+39[-.\\s]?(\\(?\\d{2,4}?\\)?[-.\\s]?)?\\d{3,4}[-.\\s]?\\d{3,4}$\r\n")
	@Column(name = "telefono")
	private String telefono;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	
	
	public RegisteredUser() {
		
	}



}