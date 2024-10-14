package com.treno.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class TrenoDto {
	
	@NotEmpty(message = "La sigla non può essere vuota.")
	private String sigla;
	
	@Min(value = 1, message = "Il numero di vagoni deve essere almeno 1.")
	private int numeroVagoni;
	
	public TrenoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TrenoDto(String sigla, int numeroVagoni) {
		super();
		this.sigla = sigla;
		this.numeroVagoni = numeroVagoni;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getNumeroVagoni() {
		return numeroVagoni;
	}

	public void setNumeroVagoni(int numeroVagoni) {
		this.numeroVagoni = numeroVagoni;
	}

	@Override
	public String toString() {
		return "TrenoDto [sigla=" + sigla + ", numeroVagoni=" + numeroVagoni + "]";
	}
	
	
	
	
	

}
