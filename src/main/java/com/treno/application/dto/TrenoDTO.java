package com.treno.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TrenoDTO {
    private long idTreno;
    private String sigla;
    private String immagine;
    private boolean inVendita;
    private double prezzoVendita;
    private String marca;
    private double mediaValutazioni;
    private double pesoTotale;
    private double postiTotali;
    private String nome;
    private long idOwner;
    private double costoTotale;


}
