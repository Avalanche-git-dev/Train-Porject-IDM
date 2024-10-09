package com.treno.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vagoni")
@Inheritance(strategy = InheritanceType.JOINED)
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

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "id_treno") // La chiave esterna per collegare i vagoni al treno + nome della chiave esterna con la convenzione.
    private Treno treno;
   
    public Vagone(int idVagone, double peso, double costo, double lunghezza) {
        this.idVagone = idVagone;
        this.peso = peso;
        this.costo = costo;
        this.lunghezza = lunghezza;
    }
    
    public Vagone() {}

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

	public Treno getTreno() {
		return treno;
	}

	public void setTreno(Treno treno) {
		this.treno = treno;
	}

}
