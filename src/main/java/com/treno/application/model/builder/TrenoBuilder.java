package com.treno.application.model.builder;

import org.springframework.stereotype.Component;

import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
//Servizio si occupa di costruire il treno. da cambiare con service .
@Component
public abstract class TrenoBuilder {

	// Da rimuovere user ?
	public final Treno crealoRapido(User user) {
		Treno treno = Treno.build();
		StringBuilder sigla = new StringBuilder();
		double costo = 0;
		treno.setOwner(user);
		treno.add(addMotrice());
		sigla.append("H");
		for (int i = 0; i < 5; i++) {
			treno.add(addCargo());
			sigla.append("C");
			treno.add(addPasseggeri());
			sigla.append("P");
		}
		treno.add(addRistorante());
		sigla.append("R");
		treno.setSigla(sigla+"");
		costo = treno.getCosto();
		treno.setPrezzoVendita(costo);
			//treno.setIdTreno(i);
			//treno.setOwner(null);
		}
		treno.add(addRistorante());
		
		return treno;
	}

	public final Treno creaTrenoDaStringa(String input) {
		String controllo = input.trim();

		Treno treno = Treno.build();

		// Usa l'assemblatore per creare i vagoni in base alla stringa
		for (char c : controllo.toCharArray()) {
			switch (c) {
			case 'H':
				treno.add(addMotrice());
				break;
			case 'C':
				treno.add(addCargo());
				break;
			case 'P':
				treno.add(addPasseggeri());
				break;
			case 'R':
				treno.add(addRistorante());
				break;
			default:
				System.out.println("Creazione del treno fallita per ora stampa, cerca le eccezioni nel vecchio progetto.");
			}
		}
		treno.setSigla(input);
		return treno;






	}

	
	
	
	
	
	
	
	
	
	
	
	protected abstract Motrice addMotrice();

	protected abstract Cargo addCargo();

	protected abstract Passeggero addPasseggeri();

	protected abstract Ristorante addRistorante();

}
