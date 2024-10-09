package com.treno.application.model.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.treno.application.configuration.FactoryConfiguration;
import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggeri;
import com.treno.application.model.Ristorante;
@Component
public class TBuilder extends TrenoBuilder {
	
	@Autowired
	private FactoryConfiguration factory;
	

	public FactoryConfiguration getFactory() {
		return factory;
	}
    
	public void setFactory(FactoryConfiguration factory) {
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
	protected Passeggeri addPasseggeri() {
		return factory.creaPasseggeri();
	}

	@Override
	protected Ristorante addRistorante() {
		return factory.creaRistorante();
	}

}
