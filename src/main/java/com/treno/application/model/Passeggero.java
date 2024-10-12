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
@Table(name= "passeggeri")
public class Passeggero extends Vagone {
	
    @Column (name= "numero_posti")
	private int numeroPosti;
    
    @Column (name = "classe")
	private String classe;
    

	public Passeggero() {
		super();
	}


	public Passeggero(int idVagone, double peso, double costo, double lunghezza, String marca, Treno treno,
			int numeroPosti, String classe) {
		super(idVagone, peso, costo, lunghezza, marca, treno);
		this.numeroPosti = numeroPosti;
		this.classe = classe;
	}


}
