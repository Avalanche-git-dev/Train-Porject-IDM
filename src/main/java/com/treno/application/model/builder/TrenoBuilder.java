package com.treno.application.model.builder;

import org.springframework.stereotype.Component;

import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;
import com.treno.application.model.Treno;

@Component
public abstract class TrenoBuilder {

	public final Treno crealoRapido() {
		Treno treno = Treno.build();
		treno.add(addMotrice());

		for (int i = 0; i < 30; i++) {
			treno.add(addCargo());
			treno.add(addPasseggeri());
		}
		treno.add(addRistorante());

		return treno;
	}

	protected abstract Motrice addMotrice();

	protected abstract Cargo addCargo();

	protected abstract Passeggero addPasseggeri();

	protected abstract Ristorante addRistorante();

}
