package com.treno.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;

@Component
public class FactoryConfiguration implements Factory {

	@Autowired
	private ApplicationContext context;
	// La marca scelta dall'utente
	private String marca;

	public void setMarca(String marca) {
		this.marca = marca.toLowerCase(); // meglio stabilire direttamente come deve essere la marca in input, anche se
											// arriva da un button. se la gestisco qui fose posso evitare il controllo
											// qui.
	}

	public Cargo creaCargo() {
		// Restituisce il bean cargo configurato per la marca selezionata, sperando che
		// basti un semplice concat
		return context.getBean(marca + "Cargo", Cargo.class);
	}

	// Funzionerà ? vediamo vorrei evitare strutture dati e iterazioni ci sarà
	// sicuramente un modo piu elegante di svolgere la cosa
	public Passeggero creaPasseggeri() {
		return context.getBean(marca + "Passeggero", Passeggero.class);
	}

	public Ristorante creaRistorante() {
		return context.getBean(marca + "Ristorante", Ristorante.class);
	}

	public Motrice creaMotrice() {
		return context.getBean(marca + "Motrice", Motrice.class);
	}

//Balza lo switch, balzano le if .
	

}
