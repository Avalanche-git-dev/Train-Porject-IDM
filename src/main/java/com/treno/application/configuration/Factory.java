package com.treno.application.configuration;

import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggeri;
import com.treno.application.model.Ristorante;

public interface Factory {
	abstract public void setMarca(String marca);
	
	abstract public Cargo creaCargo();

	abstract public Passeggeri creaPasseggeri();

	abstract public Ristorante creaRistorante();

	abstract public Motrice creaMotrice();

}
