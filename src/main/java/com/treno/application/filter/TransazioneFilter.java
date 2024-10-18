package com.treno.application.filter;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
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
	

}
