package com.treno.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dto.TrenoDTO;
import com.treno.application.exception.FondiInsufficientiException;
import com.treno.application.exception.VenditoreAcquirenteNonTrovatoException;
import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.utility.TrenoUtility;


public class TransazioneService {

    @Autowired
    @Qualifier("TransazioneDao")
    private Dao<Transazione> transazioneDao;
    
    @Autowired
    @Qualifier("TrenoDao")
    private TrenoUtility trenoDao;

    @Autowired
    @Qualifier("UserDao")
    private Dao<User> userDao;

    @Autowired
    @Qualifier("Transazione")
    private Transazione nuovaTransazione;

    // Metodo per trovare una transazione per ID (nessun cast necessario)
    public Transazione findById(long id) {
        return transazioneDao.findById(id); // Cast rimosso poiché transazioneDao è già del tipo corretto
    }

    // Metodo per trovare tutte le transazioni (nessun cast necessario)
    public List<Transazione> findAll() {
        return transazioneDao.findAll(); // Cast rimosso poiché transazioneDao è già del tipo corretto
    }

    // Metodo per comprare un treno, mantiene i parametri originali
    @Transactional
    public void compraTreno(long Iduser, long idtreno) throws VenditoreAcquirenteNonTrovatoException, FondiInsufficientiException {
        // Recupera le entità necessarie
        Treno treno = trenoDao.findById(idtreno);
        
        User venditore = treno.getOwner();
        User acquirente = userDao.findById(Iduser);

        // Controlla se venditore e acquirente esistono
        if (venditore == null || acquirente == null) {
            throw new VenditoreAcquirenteNonTrovatoException("Venditore o acquirente non trovato.");
        }

        // Controlla il prezzo e i fondi
        Double prezzo = treno.getPrezzoVendita();
        if (acquirente.getPortafoglio() < prezzo) {
            throw new FondiInsufficientiException("Fondi insufficienti per completare l'acquisto!");
        }

        // Inizializzazione di lazy collections
        Hibernate.initialize(venditore.getPortafoglio());
        Hibernate.initialize(acquirente.getPortafoglio());

        // Aggiornamento dei portafogli
        acquirente.setPortafoglio(acquirente.getPortafoglio() - prezzo);
        venditore.setPortafoglio(venditore.getPortafoglio() + prezzo);

        // Trasferimento del treno
        treno.setOwner(acquirente);
        treno.setInVendita(false);
        treno.setPrezzoVendita(0);
        
        // Crea una nuova transazione
        Transazione nuovaTransazione = new Transazione(); // Crea una nuova istanza
        nuovaTransazione.setAcquirente(acquirente);
        nuovaTransazione.setVenditore(venditore);
        nuovaTransazione.setTreno(treno);
        nuovaTransazione.setImporto(prezzo);
        nuovaTransazione.setData(LocalDateTime.now());
       
        // Salva la nuova transazione
        transazioneDao.save(nuovaTransazione);
        // Aggiorna il treno e gli utenti
        trenoDao.update(treno);
        userDao.update(acquirente);
        userDao.update(venditore);
    }


    // Metodo per mettere un treno in vendita
    @Transactional
    public String mettiInVendita(long idUtente, long idTreno, Double prezzoVendita) {
        // Recupera l'utente proprietario
        User proprietario = userDao.findById(idUtente);
        
        // Recupera il treno dal database
        Treno treno = trenoDao.findById(idTreno);

        // Inizializzazione di lazy collections
        Hibernate.initialize(treno.getVagoni());

        // Verifica che l'utente sia il proprietario del treno
        if (!treno.getOwner().equals(proprietario)) {
            return "L'utente non è il proprietario di questo treno!";
        }
        
        if (treno.isInVendita()){
            return "Il treno è già in vendita!";
        }

        // Verifica che il prezzo sia valido
        if (prezzoVendita == null || prezzoVendita <= 0) {
            return "Il prezzo di vendita deve essere maggiore di zero!";
        }

        // Imposta il treno come in vendita e assegna il prezzo
        treno.setInVendita(true);
        treno.setPrezzoVendita(prezzoVendita + treno.getCosto()); // Aggiunge il costo del treno

        // Aggiorna il treno nel database
        trenoDao.update(treno);

        return "Treno messo in vendita con successo!";
    }
}