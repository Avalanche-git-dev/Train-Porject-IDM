package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity // Specfica entity, puo essere gestita tramite entity manager
@Table(name = "vagone") // Dai un nome alla tabella
@Inheritance(strategy = InheritanceType.JOINED) // proviamo un po a vedere la joined.
public abstract class Vagone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVagone;
	@Column(name = "peso")
	private double peso;
	@Column(name = "costo")
	private double costo;
	@Column(name = "lunghezza")
	private double lunghezza;

	public Vagone(int idVagone, double peso, double costo, double lunghezza) {
		super();
		this.idVagone = idVagone;
		this.peso = peso;
		this.costo = costo;
		this.lunghezza = lunghezza;
	}

	public Vagone() {
		super();
	}

	public int getIdVagone() {
		return idVagone;
	}

	public void setIdVagone(int idVagone) {
		this.idVagone = idVagone;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}

	@Override
	public String toString() {
		return "Vagone [idVagone=" + idVagone + ", peso=" + peso + ", costo=" + costo + ", lunghezza=" + lunghezza
				+ "]";
	}

}
