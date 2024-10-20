package com.treno.application.filter;

public class TrenoFilter {

	private Double prezzoMin, prezzoMax;
	private Double pesoMin, pesoMax;
	private Double lunghezzaMin, lunghezzaMax;
	private String sigla;
	private String marca;
	private Double valutazioni;
	private Double prezzoVendita;//inteso come Max
	private Double ammontareTotale;
	private boolean inVendita;
	private String nome;
	private String nomeOwner;
	
	
	
	public String getNomeOwner() {
		return nomeOwner;
	}



	public void setNomeOwner(String nomeOwner) {
		this.nomeOwner = nomeOwner;
	}



	public TrenoFilter() {
		super();
	}






	public TrenoFilter(Double prezzoMin, Double prezzoMax, Double pesoMin, Double pesoMax, Double lunghezzaMin,
			Double lunghezzaMax, String sigla, String marca, Double valutazioni, Double prezzoVendita,
			Double ammontareTotale, boolean inVendita, String nome, String nomeOwner) {
		super();
		this.prezzoMin = prezzoMin;
		this.prezzoMax = prezzoMax;
		this.pesoMin = pesoMin;
		this.pesoMax = pesoMax;
		this.lunghezzaMin = lunghezzaMin;
		this.lunghezzaMax = lunghezzaMax;
		this.sigla = sigla;
		this.marca = marca;
		this.valutazioni = valutazioni;
		this.prezzoVendita = prezzoVendita;
		this.ammontareTotale = ammontareTotale;
		this.inVendita = inVendita;
		this.nome = nome;
		this.nomeOwner = nomeOwner;
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





	public Double getValutazioni() {
		return valutazioni;
	}



	public void setValutazioni(Double valutazioni) {
		this.valutazioni = valutazioni;
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



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}





	
	
	
	
}
