package com.treno.application.model;

public class TrenoFilter {

	private double prezzoMin, prezzoMax;
	private double pesoMin, pesoMax;
	private double lunghezzaMin, lunghezzaMax;
	private String sigla;
	
	public double getPrezzoMin() {
		return prezzoMin;
	}
	
	public void setPrezzoMin(double prezzoMin) {
		this.prezzoMin = prezzoMin;
	}
	
	public double getPrezzoMax() {
		return prezzoMax;
	}
	
	public void setPrezzoMax(double prezzoMax) {
		this.prezzoMax = prezzoMax;
	}
	
	public double getPesoMin() {
		return pesoMin;
	}
	
	public void setPesoMin(double pesoMin) {
		this.pesoMin = pesoMin;
	}
	
	public double getPesoMax() {
		return pesoMax;
	}
	
	public void setPesoMax(double pesoMax) {
		this.pesoMax = pesoMax;
	}
	
	public double getLunghezzaMin() {
		return lunghezzaMin;
	}
	
	public void setLunghezzaMin(double lunghezzaMin) {
		this.lunghezzaMin = lunghezzaMin;
	}
	
	public double getLunghezzaMax() {
		return lunghezzaMax;
	}
	
	public void setLunghezzaMax(double lunghezzaMax) {
		this.lunghezzaMax = lunghezzaMax;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
