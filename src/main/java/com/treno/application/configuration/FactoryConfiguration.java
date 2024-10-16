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
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca non può essere null o vuota");
        }
        this.marca = marca.toLowerCase(); // meglio stabilire direttamente come deve essere la marca in input, anche se
                                          // arriva da un button. se la gestisco qui fose posso evitare il controllo
    }

    public Cargo creaCargo() {
        String beanName = marca + "Cargo";
        if (!context.containsBean(beanName)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(beanName, Cargo.class);
    }

    // Funzionerà ? vediamo vorrei evitare strutture dati e iterazioni ci sarà
    // sicuramente un modo piu elegante di svolgere la cosa
    public Passeggero creaPasseggeri() {
        String beanName = marca + "Passeggero";
        if (!context.containsBean(beanName)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(beanName, Passeggero.class);
    }

    public Ristorante creaRistorante() {
        String beanName = marca + "Ristorante";
        if (!context.containsBean(beanName)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(beanName, Ristorante.class);
    }

    public Motrice creaMotrice() {
        String beanName = marca + "Motrice";
        if (!context.containsBean(beanName)) {
            throw new IllegalArgumentException("Bean non trovato per la marca: " + marca);
        }
        return context.getBean(beanName, Motrice.class);
    }
}