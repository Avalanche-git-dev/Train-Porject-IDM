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
	
    private String nome;
    private String cognome;
    private String email;
    private Integer età;
    
    
}
