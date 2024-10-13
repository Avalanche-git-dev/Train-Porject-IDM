package com.treno.application.model.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.treno.application.configuration.Factory;
import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;



public class TBuilder extends TrenoBuilder {
	
	@Autowired
	@Qualifier ("Factory")
	private Factory factory;
	

	public Factory getFactory() {
		return factory;
	}
	
	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	@Override
	protected Motrice addMotrice() {
		return factory.creaMotrice();
	}

	@Override
	protected Cargo addCargo() {
		return factory.creaCargo();
	}

	@Override
	protected Passeggero addPasseggeri() {
		return factory.creaPasseggeri();
	}

	@Override
	protected Ristorante addRistorante() {
		return factory.creaRistorante();
	}
	

}
