package com.treno.application.dto;

import com.treno.application.model.Treno;



public class TransazioneDto {
    private Treno treno;
    private Double totaleTransazioni;

    public TransazioneDto(Treno treno, Double totaleTransazioni) {
        this.treno = treno;
        this.totaleTransazioni = totaleTransazioni;
    }

    public Treno getTreno() {
        return treno;
    }

    public Double getTotaleTransazioni() {
        return totaleTransazioni;
    }

    @Override
    public String toString() {
        return "Treno: " + treno + ", Totale Transazioni: " + totaleTransazioni;
    }
}
