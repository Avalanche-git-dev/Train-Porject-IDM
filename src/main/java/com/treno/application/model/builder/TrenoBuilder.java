package com.treno.application.model.builder;

import com.treno.application.exception.MotriceInMezzoException;
import com.treno.application.exception.MotricenonInTestaException;
import com.treno.application.exception.TroppiRistorantiException;
import com.treno.application.exception.VagoniIncompatibiliException;
import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;
import com.treno.application.model.Treno;

public abstract class TrenoBuilder {
	
	
	//Treno TEST
	public final Treno crealoRapido() {
	   Treno t = Treno.build();
	   t.add(addMotrice());
	   for (int i = 0; i < 5; i++) {
		   t.add(addPasseggeri());
	   }
	   t.add(addPasseggeri());
	   return t;
	}
	
	
//	
//	
    public final Treno creaTrenoDaStringa(String input) {
    	String controllo = input.trim().toUpperCase().replaceAll("\\s", "");
 

        
        if (controllo.isEmpty() || controllo.charAt(0) != 'H') {
            throw new MotricenonInTestaException("Il treno deve iniziare con una motrice (H).");
        }

        Treno treno = Treno.build();
        boolean hasRistorante = false;
        char lastAdded = 'H';

        // Usa l'assemblatore per creare i vagoni in base alla stringa
        for (int i = 0; i < controllo.length(); i++) {
            char c = controllo.charAt(i);

            switch (c) {
                case 'H':
                    // Verifica se la motrice è in mezzo al treno
                    if (i != 0 && i != controllo.length() - 1) {
                        throw new MotriceInMezzoException("La motrice può essere solo in testa o in coda al treno.");
                    }
                    treno.add(addMotrice());
                    lastAdded = 'H';
                    break;
                case 'C':
                    // Verifica compatibilità tra Cargo e Passeggeri
                    if (lastAdded == 'P') {
                        throw new VagoniIncompatibiliException("Non è possibile combinare vagoni Passeggeri e Cargo nello stesso treno.");
                    }
                    treno.add(addCargo());
                    lastAdded = 'C';
                    break;
                case 'P':
                    // Verifica compatibilità tra Passeggeri e Cargo
                    if (lastAdded == 'C') {
                        throw new VagoniIncompatibiliException("Non è possibile combinare vagoni Cargo e Passeggeri nello stesso treno.");
                    }
                    treno.add(addPasseggeri());
                    lastAdded = 'P';
                    break;
                case 'R':
                    // Verifica che non ci sia già un ristorante
                    if (hasRistorante) {
                        throw new TroppiRistorantiException("Può esserci un solo ristorante per treno.");
                    }
                    treno.add(addRistorante());
                    hasRistorante = true;
                    lastAdded = 'R';
                    break;
                default:
                    throw new IllegalArgumentException("Carattere non riconosciuto nella stringa di input: " + c);
            }
        }
        

        treno.setSigla(input);
        return treno;
    }

    protected abstract Motrice addMotrice();

    protected abstract Cargo addCargo();

    protected abstract Passeggero addPasseggeri();

    protected abstract Ristorante addRistorante();
    
    
//    
//    public final Treno creaTrenoDaStringa(String input) {
//        String controllo = input.trim().toUpperCase().replaceAll("\\s", "");
//
//        if (controllo.isEmpty() || controllo.charAt(0) != 'H') {
//            throw new MotricenonInTestaException("Il treno deve iniziare con una motrice (H).");
//        }
//
//        Treno treno = Treno.build();
//        boolean hasRistorante = false;
//        char lastAdded = 'H';
//
//        // Inizializza la posizione per i vagoni
//        int posizioneVagone = 1;
//
//        for (int i = 0; i < controllo.length(); i++) {
//            char c = controllo.charAt(i);
//            Vagone vagone = null;
//
//            switch (c) {
//                case 'H':
//                    if (i != 0 && i != controllo.length() - 1) {
//                        throw new MotriceInMezzoException("La motrice può essere solo in testa o in coda al treno.");
//                    }
//                    vagone = addMotrice();
//                    lastAdded = 'H';
//                    break;
//                case 'C':
//                    if (lastAdded == 'P') {
//                        throw new VagoniIncompatibiliException("Non è possibile combinare vagoni Passeggeri e Cargo nello stesso treno.");
//                    }
//                    vagone = addCargo();
//                    lastAdded = 'C';
//                    break;
//                case 'P':
//                    if (lastAdded == 'C') {
//                        throw new VagoniIncompatibiliException("Non è possibile combinare vagoni Cargo e Passeggeri nello stesso treno.");
//                    }
//                    vagone = addPasseggeri();
//                    lastAdded = 'P';
//                    break;
//                case 'R':
//                    if (hasRistorante) {
//                        throw new TroppiRistorantiException("Può esserci un solo ristorante per treno.");
//                    }
//                    vagone = addRistorante();
//                    hasRistorante = true;
//                    lastAdded = 'R';
//                    break;
//                default:
//                    throw new IllegalArgumentException("Carattere non riconosciuto nella stringa di input: " + c);
//            }
//
//            if (vagone != null) {
//                // Assegna la posizione al vagone
//                vagone.setPosizione(posizioneVagone++);
//                // Assegna l'immagine in base al tipo di vagone
//                // Aggiungi il vagone al treno
//                treno.add(vagone);
//            }
//        }
//
//        treno.setSigla(input);
//        return treno;
//    }

}

