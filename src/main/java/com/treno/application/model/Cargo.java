package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "cargo")
public class Cargo extends Vagone {

	@Column(name = "volume_cargo")
	private double volumeCargo;

	public Cargo() {
		super();
	}

	public Cargo(int idVagone, double peso, double costo, double lunghezza, String marca, Treno treno,
			double volumeCargo) {
		super(idVagone, peso, costo, lunghezza, marca, treno);
		this.volumeCargo = volumeCargo;
	}

	public double getVolumeCargo() {
		return volumeCargo;
	}

	public void setVolumeCargo(double volumeCargo) {
		this.volumeCargo = volumeCargo;
	}

	@Override
	public String toString() {
		return "Cargo [volumeCargo=" + volumeCargo + ", = " + super.toString() + "]";
	}
	
	



}
