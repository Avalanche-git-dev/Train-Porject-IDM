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
@Table(name = "ristoranti")
public class Ristorante extends Vagone {
	
	@Column(name = " numero_posti_ristorante ")
	private int numeroPostiRistorante;
	@Column(name = " menu ")
	private String menu;

	public Ristorante(int idVagone, double peso, double costo, double lunghezza, String marca, Treno treno,
			int numeroPostiRistorante, String menu) {
		super(idVagone, peso, costo, lunghezza, marca, treno);
		this.numeroPostiRistorante = numeroPostiRistorante;
		this.menu = menu;
	}

	public Ristorante() {
		super();
	}



}
