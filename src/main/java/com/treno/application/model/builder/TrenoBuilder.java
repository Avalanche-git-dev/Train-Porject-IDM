
package com.treno.application.model.builder;

import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;
import com.treno.application.model.Treno;
import com.treno.application.model.Vagone;
//Servizio si occupa di costruire il treno. da cambiare con service .

public abstract class TrenoBuilder {

	public final Treno crealoRapido() {
		Treno treno = Treno.build();
		StringBuilder sb = new StringBuilder();
		treno.add(addMotrice());
		sb.append("H");
		for (int i = 0; i < 5; i++) {
			treno.add(addCargo());
			treno.add(addPasseggeri());
			sb.append("CP");
			//treno.setIdTreno(i);
			//treno.setOwner(null);
		}
		treno.add(addRistorante());
		sb.append("R");
		double prezzo = treno.getCosto();
		double lunghezza = treno.getLunghezza();
		double peso = treno.getPeso();
		treno.setPrezzoVendita(prezzo);
		treno.setLunghezza(lunghezza);
		treno.setPeso(peso);
		treno.setSigla(sb + "");
		System.out.println(prezzo);
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
