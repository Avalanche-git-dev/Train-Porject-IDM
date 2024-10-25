package com.treno.application.filter;

public class TrenoFilter {
    private String nome;
    private String sigla;
    private String marca;
    private String usernameProprietario;
    private Double pesoMin;
    private Double pesoMax;
    private Double lunghezzaMin;
    private Double lunghezzaMax;
    private Double mediaValutazioniMin;
    private Double costoTotaleMin;
    private Double costoTotaleMax;
    private String ordine; // Campo per specificare il criterio di ordinamento
    private String direzione ;

    // Getters e Setters

    public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public String getDirezione() {
		return direzione;
	}

	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUsernameProprietario() {
        return usernameProprietario;
    }

    public void setUsernameProprietario(String usernameProprietario) {
        this.usernameProprietario = usernameProprietario;
    }

    public Double getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(Double pesoMin) {
        this.pesoMin = pesoMin;
    }

    public Double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(Double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public Double getLunghezzaMin() {
        return lunghezzaMin;
    }

    public void setLunghezzaMin(Double lunghezzaMin) {
        this.lunghezzaMin = lunghezzaMin;
    }

    public Double getLunghezzaMax() {
        return lunghezzaMax;
    }

    public void setLunghezzaMax(Double lunghezzaMax) {
        this.lunghezzaMax = lunghezzaMax;
    }

    public Double getMediaValutazioniMin() {
        return mediaValutazioniMin;
    }

    public void setMediaValutazioniMin(Double mediaValutazioniMin) {
        this.mediaValutazioniMin = mediaValutazioniMin;
    }

    public Double getCostoTotaleMin() {
        return costoTotaleMin;
    }

    public void setCostoTotaleMin(Double costoTotaleMin) {
        this.costoTotaleMin = costoTotaleMin;
    }

    public Double getCostoTotaleMax() {
        return costoTotaleMax;
    }

    public void setCostoTotaleMax(Double costoTotaleMax) {
        this.costoTotaleMax = costoTotaleMax;
    }
}
