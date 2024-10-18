package com.treno.application.filter;

import com.treno.application.model.User.Stato;

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
    private Stato stato;
    
    private String telefono;
    private String nuovaPassword;
    private int numeroTreni;
    private int numeroValutazioni;
    private int numeroTransazioni;
    private int ammontareTotaleTransazioni;
    
    
    
}
