package com.treno.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    

    // Getters e Setters
	
	
}
