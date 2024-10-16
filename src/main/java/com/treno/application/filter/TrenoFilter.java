package com.treno.application.filter;

public class TrenoFilter {

	private Integer prezzoMin, prezzoMax;
	private Integer pesoMin, pesoMax;
	private Integer lunghezzaMin, lunghezzaMax;
	private String sigla;
	private String marca;
	private double Valutazioni;
	private double prezzoVendita;
	private double ammontareTotale;
	
	public double getValutazioni() {
		return Valutazioni;
	}
	public void setValutazioni(double valutazioni) {
		Valutazioni = valutazioni;
	}
	public double getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	public double getAmmontareTotale() {
		return ammontareTotale;
	}
	public void setAmmontareTotale(double ammontareTotale) {
		this.ammontareTotale = ammontareTotale;
	}
	public TrenoFilter(Integer prezzoMin, Integer prezzoMax, Integer pesoMin, Integer pesoMax, Integer lunghezzaMin,
			Integer lunghezzaMax, String sigla, String marca, double valutazioni, double prezzoVendita,
			double ammontareTotale) {
		super();
		this.prezzoMin = prezzoMin;
		this.prezzoMax = prezzoMax;
		this.pesoMin = pesoMin;
		this.pesoMax = pesoMax;
		this.lunghezzaMin = lunghezzaMin;
		this.lunghezzaMax = lunghezzaMax;
		this.sigla = sigla;
		this.marca = marca;
		Valutazioni = valutazioni;
		this.prezzoVendita = prezzoVendita;
		this.ammontareTotale = ammontareTotale;
	}
	public TrenoFilter() {
		super();
	}
	public Integer getPrezzoMin() {
		return prezzoMin;
	}
	public void setPrezzoMin(Integer prezzoMin) {
		this.prezzoMin = prezzoMin;
	}
	public Integer getPrezzoMax() {
		return prezzoMax;
	}
	public void setPrezzoMax(Integer prezzoMax) {
		this.prezzoMax = prezzoMax;
	}
	public Integer getPesoMin() {
		return pesoMin;
	}
	public void setPesoMin(Integer pesoMin) {
		this.pesoMin = pesoMin;
	}
	public Integer getPesoMax() {
		return pesoMax;
	}
	public void setPesoMax(Integer pesoMax) {
		this.pesoMax = pesoMax;
	}
	public Integer getLunghezzaMin() {
		return lunghezzaMin;
	}
	public void setLunghezzaMin(Integer lunghezzaMin) {
		this.lunghezzaMin = lunghezzaMin;
	}
	public Integer getLunghezzaMax() {
		return lunghezzaMax;
	}
	public void setLunghezzaMax(Integer lunghezzaMax) {
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
	
	
	
	
	
	
}
