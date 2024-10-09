package com.treno.application.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="Market")
public class Market {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_market")
    private Integer idMarket;

    @OneToMany(mappedBy="market", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    private List<Treno> treniInVendita;

    public Market() {
        this.treniInVendita = new LinkedList<>();
    }

    public Integer getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(Integer idMarket) {
        this.idMarket = idMarket;
    }

    public List<Treno> getTreniInVendita() {
        return treniInVendita;
    }

    public void setTreniInVendita(List<Treno> treniInVendita) {
        this.treniInVendita = treniInVendita;
    }

    public void addTrenoInVendita(Treno treno) {
        treniInVendita.add(treno);
        treno.setMarket(this); // Associa il treno a questo mercato
    }

    public void removeDaTreniInVendita(Treno treno) {
        treniInVendita.remove(treno);
        treno.setMarket(null); // Rimuove l'associazione
    }
    
}

