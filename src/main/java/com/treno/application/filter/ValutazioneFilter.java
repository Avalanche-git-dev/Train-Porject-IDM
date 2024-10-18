package com.treno.application.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValutazioneFilter {
	
	
	private int votoMassimo;
	private int votoMinimo;
	private int mediaVoti;
	public ValutazioneFilter(int votoMassimo, int votoMinimo, int mediaVoti) {
		super();
		this.votoMassimo = votoMassimo;
		this.votoMinimo = votoMinimo;
		this.mediaVoti = mediaVoti;
	}
	public ValutazioneFilter() {
		super();
	}
	

}
