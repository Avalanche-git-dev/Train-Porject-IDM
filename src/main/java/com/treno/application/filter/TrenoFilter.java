package com.treno.application.filter;

public class TrenoFilter {

	private Integer prezzoMin, prezzoMax;
	private Integer pesoMin, pesoMax;
	private Integer lunghezzaMin, lunghezzaMax;
	private String sigla;
	
	public double getPrezzoMin() {
		return prezzoMin;
	}
	
	public void setPrezzoMin(int prezzoMin) {
		this.prezzoMin = prezzoMin;
	}
	
	public double getPrezzoMax() {
		return prezzoMax;
	}
	
	public void setPrezzoMax(int prezzoMax) {
		this.prezzoMax = prezzoMax;
	}
	
	public double getPesoMin() {
		return pesoMin;
	}
	
	public void setPesoMin(int pesoMin) {
		this.pesoMin = pesoMin;
	}
	
	public double getPesoMax() {
		return pesoMax;
	}
	
	public void setPesoMax(int pesoMax) {
		this.pesoMax = pesoMax;
	}
	
	public double getLunghezzaMin() {
		return lunghezzaMin;
	}
	
	public void setLunghezzaMin(int lunghezzaMin) {
		this.lunghezzaMin = lunghezzaMin;
	}
	
	public double getLunghezzaMax() {
		return lunghezzaMax;
	}
	
	public void setLunghezzaMax(int lunghezzaMax) {
		this.lunghezzaMax = lunghezzaMax;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
