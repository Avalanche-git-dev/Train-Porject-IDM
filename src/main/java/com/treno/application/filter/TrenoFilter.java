package com.treno.application.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrenoFilter {

	private Double prezzoMin, prezzoMax;
	private Double pesoMin, pesoMax;
	private Double lunghezzaMin, lunghezzaMax;
	private String sigla;
	private String marca;
	private Double Valutazioni;
	private Double prezzoVendita;
	private Double ammontareTotale;
	private boolean inVendita;
	private String nome;
	
	
	
	public TrenoFilter() {
		super();
	}



	public TrenoFilter(Double prezzoMin, Double prezzoMax, Double pesoMin, Double pesoMax, Double lunghezzaMin,
			Double lunghezzaMax, String sigla, String marca, Double valutazioni, Double prezzoVendita,
			Double ammontareTotale, boolean inVendita, String nome) {
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
		this.inVendita = inVendita;
		this.nome = nome;
	}





	
	
	
	
}
