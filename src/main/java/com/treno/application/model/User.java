package com.treno.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    // Definiamo ruolo come enum
    @Enumerated(EnumType.STRING)
    @Column(name = "ruolo", nullable = false)
    private Ruolo ruolo;
    
    // if ruolo == "ADMIN" userId = "ADMINn" else userID = "USERn"

    // @Column(name = "stato", nullable = false, length = 10)
    // private boolean stato;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "cognome", length = 50)
    private String cognome;

    @Column(name = "numero_telefono", length = 20)
    private String numeroTelefono;

    public User() {}

    public User(String username, String password, String email, Ruolo ruolo, String nome, String cognome, String numeroTelefono) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.ruolo = ruolo;
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTelefono = numeroTelefono;
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

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
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

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

}
