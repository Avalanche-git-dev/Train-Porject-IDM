package com.treno.application.model;

import java.util.LinkedList;
import java.util.List;

import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;

public class User {

	private int idUser;
	private List<Treno> l = new LinkedList<Treno>();
	private boolean stato;
	private double portafoglio;
    // Da Testare bene, costruisce un treno tramite il trenoBuilder passando la stringa

	public void creaTreno(String input) {
		TrenoBuilder b = new TBuilder();
		Treno t = b.crealoRapido();//Immaginiamo creaTrenoDaStringa(input) 
		l.add(t);
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<Treno> getL() {
		return l;
	}

	public void setL(List<Treno> l) {
		this.l = l;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public double getPortafoglio() {
		return portafoglio;
	}

	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}
    // Duplica Treno tramite getTreno.
	public Treno duplicaTreno (Treno t) {
		Treno copia = Treno.build();
	    copia=t.getTreno();
	    return copia;
	}
	
	
	
	

}