package com.treno.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.TrenoDao;
import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
@Component
@Scope("prototype")
public class TransazioneService {

	@Autowired
	@Qualifier("transazioneDao")
	private Dao<Transazione> transazioneDao;

	@Autowired
	@Qualifier("trenoDao")
	private Dao<Treno> trenoDao;
	
//	@Autowired
//	private Transazione market;
	
	@Autowired
	@Qualifier("trenoDao")
	private Dao<User> userDao;



	public Dao<Transazione> getTransazioneDao() {
		return transazioneDao;
	}

	public void setTransazioneDao(Dao<Transazione> transazioneDao) {
		this.transazioneDao = transazioneDao;
	}

	public Dao<Treno> getTrenoDao() {
		return trenoDao;
	}

	public void setTrenoDao(Dao<Treno> trenoDao) {
		this.trenoDao = trenoDao;
	}


	public Dao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(Dao<User> userDao) {
		this.userDao = userDao;
	}

	public void setTrenoDao(TrenoDao trenoDao) {
		this.trenoDao = trenoDao;
	}

	public List<Treno> getAllTreniV() {
		return ((TrenoDao) trenoDao).findAllV();
	}

	public TransazioneService() {
		super();
	}

	@Transactional
	public void mettereInVendita(Treno treno, User venditore) throws Exception {
		// Controlla se il treno è già in vendita
		if (treno.getInVendita()) {
			throw new IllegalArgumentException("Il treno è già in vendita.");
		}
		// Controlla se effettivamente il propietaro è il venditore.
		if(treno.getOwner()!=venditore) {
			throw new IllegalAccessException("non puoi vendere un treno che non è un tuo :( ");
		}

		treno.setInVendita(true);
		trenoDao.update(treno);
		
	}
	public void compraTreno(User compratore,Treno treno) throws Exception {
        // Recupero deelle entità che mi servono dal db
        compratore =  userDao.findById(compratore.getUserId()); //aggiungere eccezioni 
        treno = trenoDao.findById(treno.getIdTreno());// idem aggiubngere eccezzioni in caso di not found.
        
        User venditore = treno.getOwner();

        // Evrifico che il treno sia in vendita
        if (!treno.getInVendita()) {
            throw new Exception("Il treno non è  piu in vendita");
        }

        // controllo che il compratore non sia allo stesso momento il venditore.
        if (compratore.equals(venditore)) {
            throw new Exception("Non puoi acquistare il tuo stesso treno..");
        }

        // Controllo saldo compratore
        if (compratore.getPortafoglio() < treno.getPrezzoVendita()) {
            throw new Exception("Saldo insufficiente");
        }

        // passaggio di soldi
        compratore.setPortafoglio(compratore.getPortafoglio() - treno.getPrezzoVendita());
        venditore.setPortafoglio(venditore.getPortafoglio()+ treno.getPrezzoVendita());

        // Trasferisci la proprietà del treno
        treno.setOwner(compratore);
        treno.setInVendita(false);  // Imposta il treno come non più in vendita
        trenoDao.save(treno);

        // Registra la transazione con i dettagli di compratore e venditore
        Transazione transazione = new Transazione();
        transazione.setAcquirente(compratore);
        transazione.setVenditore(venditore);
        transazione.setTreno(treno);
        transazione.setImporto(treno.getPrezzoVendita());
        transazione.setData(LocalDateTime.now());

       

        transazioneDao.save(transazione);

        // Aggiorna saldo utenti
        userDao.save(compratore);
        userDao.save(venditore);
    }

	
	
	
	
	
	

//	@Transactional
//	public void acquistaTreno(Transazione market, User acquirente, double prezzo) {
//		// Controlli di concorrenza e validità
//		if (market == null || market.getTrenoInVendita() == null) {
//			throw new IllegalArgumentException("Transazione non valida. Il treno non esiste.");
//		}
//
//		if (market.getAcquirente() != null) {
//			throw new IllegalStateException("Il treno è già stato acquistato da un altro utente.");
//		}
//
//		// Controlla se il treno è ancora in vendita
//		if (!market.getTrenoInVendita().getInVendita()) {
//			throw new IllegalArgumentException("Il treno non è più disponibile per l'acquisto.");
//		}
//
//		// Registra l'acquirente, il prezzo e imposta la data della transazione
//		market.setAcquirente(acquirente);
//		market.setAmount(prezzo);
//		market.setTransactionDate(LocalDateTime.now());
//
//		market.getTrenoInVendita().setInVendita(false);
//		// Imposto il riferimento del nuovo propietario
//		Treno t = market.getTrenoInVendita();
//		t.setOwner(acquirente);
//
//		trenoDao.update(t);
//		marketDao.update(market);
//
//		registraTransazione(market);
//	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
//	@Transactional
//	public void registraTransazione(Transazione market) {
//		if (market.getAcquirente() == null || market.getAmount() == null) {
//			throw new IllegalArgumentException("Transazione incompleta. Manca l'acquirente o il prezzo.");
//		}
//
//		// Aggiunge il passaggio di proprietà del treno
//		Treno treno = market.getTrenoInVendita();
//		User venditore = market.getVenditore();
//		User acquirente = market.getAcquirente();
//
//		// Aggiorna lo stato del treno come non in vendita
//		treno.setInVendita(false);
//		trenoDao.update(treno);
//
//		// Salva/aggiorna il market nel database (transazione completata)
//		marketDao.update(market);
//
//		System.out.println("Transazione registrata: " + venditore.getUsername() + " ha venduto un treno a "
//				+ acquirente.getUsername() + " per €" + market.getAmount());
//	}

//	@Transactional
//	public void rimuoviDallaVendita(Treno treno, User venditore) {
//		// Controllo se il treno è in vendita
//		if (!treno.getInVendita()) {
//			throw new IllegalArgumentException("Il treno non è in vendita.");
//		}
//
//		// controllo se il venditore è lo stesso proprietario che ha messo in vendita il
//		// treno
//		Transazione market = ((TransazioneDao) marketDao).findTransactionByTreno(treno);
//		if (market == null || !market.getVenditore().equals(venditore)) {
//			throw new SecurityException("Non sei autorizzato a rimuovere questo treno dalla vendita.");
//		}
//
//		// tolgo il treno dalla vendita
//		treno.setInVendita(false);
//		trenoDao.update(treno);
//
//		// tolgo l'inserimento di vendita dal database
//		marketDao.delete(market);
//	}

}
