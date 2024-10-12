package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ristoranti")
public class Ristorante extends Vagone {
	@Column(name = " numero_posti_ristorante ")
	private int numeroPostiRistorante;
	@Column(name = " menu ")
	private String menu;

	public Ristorante(int idVagone, double peso, double costo, double lunghezza, int numeroPostiRistorante,
			String menu) {
		super(idVagone, peso, costo, lunghezza);
		this.numeroPostiRistorante = numeroPostiRistorante;
		this.menu = menu;
	}

	public Ristorante() {
		super();
	}

	public int getNumeroPostiRistorante() {
		return numeroPostiRistorante;
	}

	public void setNumeroPostiRistorante(int numeroPostiRistorante) {
		this.numeroPostiRistorante = numeroPostiRistorante;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "Ristorante [numeroPostiRistorante=" + numeroPostiRistorante + ", menu=" + menu + ", ="
				+ super.toString() + "]";
	}

}
