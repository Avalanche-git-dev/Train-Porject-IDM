package com.treno.application.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

//Entity

@Component
public class Treno {

	private List<Vagone> vagoni = new LinkedList<>();

	private Treno(List<Vagone> vagoni) {
		super();
		this.vagoni = vagoni;
	}
    //costruttore privato
	private Treno() {
	}
    //metodo static per la costruzione in tbuilder
	public static Treno build() {
		return new Treno();
	}
    //getTrenoImmutabile
	public Treno getTreno() {
		return new Treno(Collections.unmodifiableList(vagoni));// Restituisce una copia immutabile del treno.
	}

	public List<Vagone> getVagoni() {
		return vagoni;
	}
  
	public void setVagoni(List<Vagone> vagoni) {
		this.vagoni = vagoni;
	}
    //addVagone
	public void add(Vagone vagone) {
		vagoni.add(vagone);
	}

	@Override
	public String toString() {
		return "Treno [vagoni=" + vagoni + "]";
	}

//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		for (Vagone vagone : vagoni) {
//			sb.append(vagone/*.getClass().getSimpleName())*/.append(" - ").append(vagone.toString()).append("\n");
//		}
//		return sb.toString();
//	}

}