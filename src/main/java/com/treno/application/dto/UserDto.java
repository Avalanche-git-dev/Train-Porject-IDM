package com.treno.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDto {
	
	private int userId;
    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    private String telefono;
    

    // Getters e Setters
	
	
}
