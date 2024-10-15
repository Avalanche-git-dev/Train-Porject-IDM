package com.treno.application.filter;

public class TrenoFilter {

	private Integer prezzoMin, prezzoMax;
	private Integer pesoMin, pesoMax;
	private Integer lunghezzaMin, lunghezzaMax;
	private String sigla;
	private String marca;
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
	public TrenoFilter() {
		super();
	}
	
	
	
}
