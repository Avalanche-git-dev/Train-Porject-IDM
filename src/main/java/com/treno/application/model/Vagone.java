package com.treno.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
@Table(name = "vagoni")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vagone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVagone;

    @Column(name = "peso")
    private double peso;

    @Column(name = "costo")
    private double costo;

    @Column(name = "lunghezza")
    private double lunghezza;
    
    @Column(name= "marca")
    private String marca;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "id_treno") // La chiave esterna per collegare i vagoni al treno + nome della chiave esterna con la convenzione.
    private Treno treno;

	public Vagone() {
		super();
	}

	public Vagone(int idVagone, double peso, double costo, double lunghezza, String marca, Treno treno) {
		super();
		this.idVagone = idVagone;
		this.peso = peso;
		this.costo = costo;
		this.lunghezza = lunghezza;
		this.marca = marca;
		this.treno = treno;
	}
	
	
   

	

}
