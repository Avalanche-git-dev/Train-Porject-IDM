package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "motrici") // Nome della tabella specifica per la motrice
public class Motrice extends Vagone {

	@Column(name = "peso_trainabile")
	private double pesoTrainabile;

	@Column(name = "tipo_motore")
	private String tipoMotore;


	public Motrice() {
		super();
	}


	public Motrice(int idVagone, double peso, double costo, double lunghezza, String marca, Treno treno,
			double pesoTrainabile, String tipoMotore) {
		super(idVagone, peso, costo, lunghezza, marca, treno);
		this.pesoTrainabile = pesoTrainabile;
		this.tipoMotore = tipoMotore;
	}
	
	


}
