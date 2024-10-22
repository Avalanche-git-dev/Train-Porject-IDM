package com.treno.application.dto;

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
    private double lunghezza;
	public TrenoDTO(long idTreno, String sigla, String immagine, boolean inVendita, double prezzoVendita, String marca,
			double mediaValutazioni, double pesoTotale, double postiTotali, String nome, long idOwner,
			double costoTotale, double lunghezza) {
		super();
		this.idTreno = idTreno;
		this.sigla = sigla;
		this.immagine = immagine;
		this.inVendita = inVendita;
		this.prezzoVendita = prezzoVendita;
		this.marca = marca;
		this.mediaValutazioni = mediaValutazioni;
		this.pesoTotale = pesoTotale;
		this.postiTotali = postiTotali;
		this.nome = nome;
		this.idOwner = idOwner;
		this.costoTotale = costoTotale;
		this.lunghezza = lunghezza;
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
		return mediaValutazioni;
	}
	public void setMediaValutazioni(double mediaValutazioni) {
		this.mediaValutazioni = mediaValutazioni;
	}
	public double getPesoTotale() {
		return pesoTotale;
	}
	public void setPesoTotale(double pesoTotale) {
		this.pesoTotale = pesoTotale;
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
				+ mediaValutazioni + ", pesoTotale=" + pesoTotale + ", postiTotali=" + postiTotali + ", nome=" + nome
				+ ", idOwner=" + idOwner + ", costoTotale=" + costoTotale + "]";
	}
	public double getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}


}
