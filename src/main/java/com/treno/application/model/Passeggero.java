package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name= "passeggeri")
public class Passeggero extends Vagone {
	
    @Column (name= "numero_posti")
	private int numeroPosti;
    @Column (name = "classe")
	private String classe;


	public Passeggero(int idVagone, double peso, double costo, double lunghezza, int numeroPosti, String classe) {
		super(idVagone, peso, costo, lunghezza);
		this.numeroPosti = numeroPosti;
		this.classe = classe;
	}

	public Passeggero() {
		super();
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public String toString() {
		return "Passeggeri [numeroPosti =" + numeroPosti + ", classe =" + classe + ", = " + super.toString() + "]";
	}

}
