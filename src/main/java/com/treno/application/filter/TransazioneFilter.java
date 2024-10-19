package com.treno.application.filter;

import java.time.LocalDateTime;
public class TransazioneFilter {
	
	private double importoMassimo;
	private double importoMinimo;
	private LocalDateTime ultimaTransazione;
	public TransazioneFilter(double importoMassimo, double importoMinimo, LocalDateTime ultimaTransazione) {
		super();
		this.importoMassimo = importoMassimo;
		this.importoMinimo = importoMinimo;
		this.ultimaTransazione = ultimaTransazione;
	}
	public TransazioneFilter() {
		super();
	}
	public double getImportoMassimo() {
		return importoMassimo;
	}
	public void setImportoMassimo(double importoMassimo) {
		this.importoMassimo = importoMassimo;
	}
	public double getImportoMinimo() {
		return importoMinimo;
	}
	public void setImportoMinimo(double importoMinimo) {
		this.importoMinimo = importoMinimo;
	}
	public LocalDateTime getUltimaTransazione() {
		return ultimaTransazione;
	}
	public void setUltimaTransazione(LocalDateTime ultimaTransazione) {
		this.ultimaTransazione = ultimaTransazione;
	}
	@Override
	public String toString() {
		return "TransazioneFilter [importoMassimo=" + importoMassimo + ", importoMinimo=" + importoMinimo
				+ ", ultimaTransazione=" + ultimaTransazione + "]";
	}
	

}
