package com.treno.application.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UtenteFilter {
	
	
	//Filtri per utente
    private String nome;
    private String cognome;
    private String email;
    private Integer età;
    
    
    //Filtri per admin
    private String username;
    private String password;
    private String attivita;
    
    
    
}
