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
	private Double prezzoMin, prezzoMax;
	private Double valutazioneMin, valutazioneMax;
	private Double prezzoVendita;//inteso come Max
	private Double ammontareTotale;
	private boolean inVendita;
	private Long idOwner;
	private Long idTreno;
	private String ordinamento;
	private boolean ascendente;
	
	
	
	public Double getValutazioneMin() {
		return valutazioneMin;
	}



	public void setValutazioneMin(Double valutazioneMin) {
		this.valutazioneMin = valutazioneMin;
	}



	public Double getValutazioneMax() {
		return valutazioneMax;
	}



	public void setValutazioneMax(Double valutazioneMax) {
		this.valutazioneMax = valutazioneMax;
	}



	public Long getIdOwner() {
		return idOwner;
	}



	public void setIdOwner(Long idOwner) {
		this.idOwner = idOwner;
	}
    public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public String getDirezione() {
		return direzione;
	}





	public Double getPrezzoMin() {
		return prezzoMin;
	}



	public void setPrezzoMin(Double prezzoMin) {
		this.prezzoMin = prezzoMin;
	}



	public Double getPrezzoMax() {
		return prezzoMax;
	}



	public void setPrezzoMax(Double prezzoMax) {
		this.prezzoMax = prezzoMax;
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



	public Double getPrezzoVendita() {
		return prezzoVendita;
	}



	public void setPrezzoVendita(Double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}



	public Double getAmmontareTotale() {
		return ammontareTotale;
	}



	public void setAmmontareTotale(Double ammontareTotale) {
		this.ammontareTotale = ammontareTotale;
	}



	public boolean isInVendita() {
		return inVendita;
	}



	public void setInVendita(boolean inVendita) {
		this.inVendita = inVendita;
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


	public Long getIdTreno() {
		return idTreno;
	}



	public void setIdTreno(Long idTreno) {
		this.idTreno = idTreno;
	}



	public boolean isAscendente() {
		return ascendente;
	}



	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}



	public String getOrdinamento() {
		return ordinamento;
	}



	public void setOrdinamento(String ordinamento) {
		this.ordinamento = ordinamento;
	}



    public String getUsernameProprietario() {
        return usernameProprietario;
    }

    public void setUsernameProprietario(String usernameProprietario) {
        this.usernameProprietario = usernameProprietario;
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
