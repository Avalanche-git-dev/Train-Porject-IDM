package com.treno.application.dto;

import java.util.ArrayList;
import java.util.List;
import com.treno.application.model.Vagone;

public class TrenoDTO {

    private long idTreno;
    private String sigla;
    private String immagine;
    private boolean inVendita;
    private double prezzoVendita;
    private String marca;
    private double valutazioneMedia;
    private double pesoTotale;
    private double lunghezzaTotale;
    private double postiTotali;
    private String nome;
    private long idOwner;
    private double costoTotale;
	private List<Vagone> vagoni;
	private Double valore;
	
	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public List<Vagone> getVagoni() {
		return vagoni;
	}

	public void setVagoni(List<Vagone> vagoni) {
		this.vagoni = new ArrayList<Vagone>(vagoni);
	}


	public TrenoDTO(long idTreno, String sigla, String immagine, boolean inVendita, double prezzoVendita, String marca,
			double mediaValutazioni, double pesoTotale, double lunghezzaTotale, double postiTotali, String nome,
			long idOwner, double costoTotale, List<Vagone> vagoni, Double valore, double valutazioneMedia) {
		super();
		this.idTreno = idTreno;
		this.sigla = sigla;
		this.immagine = immagine;
		this.inVendita = inVendita;
		this.prezzoVendita = prezzoVendita;
		this.marca = marca;
		this.valutazioneMedia = valutazioneMedia;
		this.pesoTotale = pesoTotale;
		this.lunghezzaTotale = lunghezzaTotale;
		this.postiTotali = postiTotali;
		this.nome = nome;
		this.idOwner = idOwner;
		this.costoTotale = costoTotale;
		this.vagoni = vagoni;
		this.valore = valore;
	}

	public TrenoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdTreno() {
		return idTreno;
	}

	public void setIdTreno(long idTreno) {
		this.idTreno = idTreno;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public boolean isInVendita() {
		return inVendita;
	}

	public void setInVendita(boolean inVendita) {
		this.inVendita = inVendita;
	}

	public double getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getMediaValutazioni() {
		return valutazioneMedia;
	}

	public void setValutazioneMedia(double valutazioneMedia) {
		this.valutazioneMedia = valutazioneMedia;
	}

	public double getPesoTotale() {
		return pesoTotale;
	}

	public void setPesoTotale(double pesoTotale) {
		this.pesoTotale = pesoTotale;
	}

	public double getLunghezzaTotale() {
		return lunghezzaTotale;
	}

	public void setLunghezzaTotale(double lunghezzaTotale) {
		this.lunghezzaTotale = lunghezzaTotale;
	}

	public double getPostiTotali() {
		return postiTotali;
	}

	public void setPostiTotali(double postiTotali) {
		this.postiTotali = postiTotali;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(long idOwner) {
		this.idOwner = idOwner;
	}

	public double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}
	@Override
	public String toString() {
		return "TrenoDTO [idTreno=" + idTreno + ", sigla=" + sigla + ", immagine=" + immagine + ", inVendita="
				+ inVendita + ", prezzoVendita=" + prezzoVendita + ", marca=" + marca + ", mediaValutazioni="
				+ valutazioneMedia + ", pesoTotale=" + pesoTotale + ", lunghezzaTotale=" + lunghezzaTotale 
				+ ", postiTotali=" + postiTotali + ", nome=" + nome + ", idOwner=" + idOwner + ", costoTotale=" 
				+ costoTotale + "]";
	}


}
