package com.treno.application;

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
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca non può essere null o vuota");
        }
        this.marca = marca.toLowerCase(); // meglio stabilire direttamente come deve essere la marca in input, anche se
                                          // arriva da un button. se la gestisco qui fose posso evitare il controllo
    }

    public Cargo creaCargo() {
        String vagoneCargo = marca + "Cargo";
        if (!context.containsBean(vagoneCargo)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(vagoneCargo, Cargo.class);
    }

    // Funzionerà ? vediamo vorrei evitare strutture dati e iterazioni ci sarà
    // sicuramente un modo piu elegante di svolgere la cosa
    public Passeggero creaPasseggeri() {
        String vagonePasseggeri = marca + "Passeggero";
        if (!context.containsBean(vagonePasseggeri)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(vagonePasseggeri, Passeggero.class);
    }

    public Ristorante creaRistorante() {
        String vagoneRistorante = marca + "Ristorante";
        if (!context.containsBean(vagoneRistorante)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(vagoneRistorante, Ristorante.class);
    }

    public Motrice creaMotrice() {
        String vagoneMotrice = marca + "Motrice";
        if (!context.containsBean(vagoneMotrice)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(vagoneMotrice, Motrice.class);
    }
}