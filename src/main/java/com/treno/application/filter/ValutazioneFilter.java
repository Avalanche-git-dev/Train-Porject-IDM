package com.treno.application.filter;

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
	public int getVotoMassimo() {
		return votoMassimo;
	}
	public void setVotoMassimo(int votoMassimo) {
		this.votoMassimo = votoMassimo;
	}
	public int getVotoMinimo() {
		return votoMinimo;
	}
	public void setVotoMinimo(int votoMinimo) {
		this.votoMinimo = votoMinimo;
	}
	public int getMediaVoti() {
		return mediaVoti;
	}
	public void setMediaVoti(int mediaVoti) {
		this.mediaVoti = mediaVoti;
	}
	@Override
	public String toString() {
		return "ValutazioneFilter [votoMassimo=" + votoMassimo + ", votoMinimo=" + votoMinimo + ", mediaVoti="
				+ mediaVoti + "]";
	}
	

}
