package com.treno.application.filter;

public class TrenoFilter {

	
	
	private Double pesoMin, pesoMax;//generale
	private Double lunghezzaMin, lunghezzaMax;//generale
	private String sigla; //generale
	private String marca; //generale
	private String nomeTreno; //generale
	private String nomeOwner;  //generale
	private Double valutazioni; //generale
	
	
	
	
	
	private Double valoreMax; // market
	private Double valoreMin;// market
	private Double ammontareTotale; // market
	private Double prezzoMin, prezzoMax;//market
	private Double prezzoVendita;  //market
	
	
	
	
	
	
	
	public TrenoFilter(Double pesoMin, Double pesoMax, Double lunghezzaMin, Double lunghezzaMax, String sigla,
			String marca, String nomeTreno, String nomeOwner, Double valutazioni, Double valoreMax, Double valoreMin,
			Double ammontareTotale, Double prezzoMin, Double prezzoMax, Double prezzoVendita) {
		super();
		this.pesoMin = pesoMin;
		this.pesoMax = pesoMax;
		this.lunghezzaMin = lunghezzaMin;
		this.lunghezzaMax = lunghezzaMax;
		this.sigla = sigla;
		this.marca = marca;
		this.nomeTreno = nomeTreno;
		this.nomeOwner = nomeOwner;
		this.valutazioni = valutazioni;
		this.valoreMax = valoreMax;
		this.valoreMin = valoreMin;
		this.ammontareTotale = ammontareTotale;
		this.prezzoMin = prezzoMin;
		this.prezzoMax = prezzoMax;
		this.prezzoVendita = prezzoVendita;
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
	public String getNomeTreno() {
		return nomeTreno;
	}
	public void setNomeTreno(String nomeTreno) {
		this.nomeTreno = nomeTreno;
	}
	public String getNomeOwner() {
		return nomeOwner;
	}
	public void setNomeOwner(String nomeOwner) {
		this.nomeOwner = nomeOwner;
	}
	public Double getValutazioni() {
		return valutazioni;
	}
	public void setValutazioni(Double valutazioni) {
		this.valutazioni = valutazioni;
	}
	public Double getValoreMax() {
		return valoreMax;
	}
	public void setValoreMax(Double valoreMax) {
		this.valoreMax = valoreMax;
	}
	public Double getValoreMin() {
		return valoreMin;
	}
	public void setValoreMin(Double valoreMin) {
		this.valoreMin = valoreMin;
	}
	public Double getAmmontareTotale() {
		return ammontareTotale;
	}
	public void setAmmontareTotale(Double ammontareTotale) {
		this.ammontareTotale = ammontareTotale;
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
	public Double getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(Double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	
	
	
	








	









}