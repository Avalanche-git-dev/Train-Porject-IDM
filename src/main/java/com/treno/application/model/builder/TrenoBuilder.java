package com.treno.application.model.builder;

import org.springframework.stereotype.Component;

import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggeri;
import com.treno.application.model.Ristorante;
import com.treno.application.model.Treno;
@Component
public abstract class TrenoBuilder {
	
	public final Treno crealoRapido() {
		Treno treno = Treno.build();
		treno.add(addMotrice());
		treno.add(addCargo());
		treno.add(addRistorante());
		for (int i = 0; i < 5; i++) {
			treno.add(addPasseggeri());
		}
		return treno;
	}
	



protected abstract Motrice addMotrice();

protected abstract Cargo addCargo();

protected abstract Passeggeri addPasseggeri();

protected abstract Ristorante addRistorante();

}
