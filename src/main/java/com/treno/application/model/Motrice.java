package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//@Component
//@Scope("prototype")
@Entity
@Table(name = "motrice") // Nome della tabella specifica per la motrice
public class Motrice extends Vagone {
	
	@Column(name = "peso_trainabile")
	private double pesoTrainabile;
	
	@Column(name = "tipo_motore")
	private String tipoMotore;


	public Motrice(int idVagone, double peso, double costo, double lunghezza, double pesoTrainabile,
			String tipoMotore) {
		super(idVagone, peso, costo, lunghezza);
		this.pesoTrainabile = pesoTrainabile;
		this.tipoMotore = tipoMotore;
	}

	public Motrice() {
		super();
	}

	public double getPesoTrainabile() {
		return pesoTrainabile;
	}

	public void setPesoTrainabile(double pesoTrainabile) {
		this.pesoTrainabile = pesoTrainabile;
	}

	public String getTipoMotore() {
		return tipoMotore;
	}

	public void setTipoMotore(String tipoMotore) {
		this.tipoMotore = tipoMotore;
	}

	@Override
	public String toString() {
		return "Motrice [pesoTrainabile=" + pesoTrainabile + ", tipoMotore=" + tipoMotore + ", =" + super.toString()
				+ "]";
	}

}
