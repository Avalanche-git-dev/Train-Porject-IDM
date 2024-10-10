package com.treno.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.TransazioneDao;
import com.treno.application.dao.TrenoDao;
import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
@Component
@Scope("prototype")
public class TransazioneService {

	@Autowired
	@Qualifier("transazioneDao")
	private Dao<Transazione> marketDao;

	@Autowired
	@Qualifier("trenoDao")
	private Dao<Treno> trenoDao;
	
	@Autowired
	private Transazione market;


	public Dao<Transazione> getMarketDao() {
		return marketDao;
	}

	public void setMarketDao(Dao<Transazione> marketDao) {
		this.marketDao = marketDao;
	}

	public Dao<Treno> getTrenoDao() {
		return trenoDao;
	}

	public void setTrenoDao(Dao<Treno> trenoDao) {
		this.trenoDao = trenoDao;
	}

	public Transazione getMarket() {
		return market;
	}

	public void setMarket(Transazione market) {
		this.market = market;
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
	public void mettereInVendita(Treno treno, User venditore) {
		// Controlla se il treno è già in vendita
		if (treno.getInVendita()) {
			throw new IllegalArgumentException("Il treno è già in vendita.");
		}

		// Crea transazione (transazione in corso) però vorrei gestire con un bean
		// vedremo.
		 
		treno.setInVendita(true);
		market.setTrenoInVendita(treno);
		market.setVenditore(venditore);
		market.setTransactionDate(LocalDateTime.now()); // Data forse la toglierò ..

		// Imposta il treno come in vendita

		trenoDao.update(treno); // Aggiorna lo stato del treno giustamente

		// Salva l'inserimento di vendita nel database tramite dao
		marketDao.save(market);
	}

	@Transactional
	public void acquistaTreno(Transazione market, User acquirente, double prezzo) {
		// Controlli di concorrenza e validità
		if (market == null || market.getTrenoInVendita() == null) {
			throw new IllegalArgumentException("Transazione non valida. Il treno non esiste.");
		}

		if (market.getAcquirente() != null) {
			throw new IllegalStateException("Il treno è già stato acquistato da un altro utente.");
		}

		// Controlla se il treno è ancora in vendita
		if (!market.getTrenoInVendita().getInVendita()) {
			throw new IllegalArgumentException("Il treno non è più disponibile per l'acquisto.");
		}

		// Registra l'acquirente, il prezzo e imposta la data della transazione
		market.setAcquirente(acquirente);
		market.setAmount(prezzo);
		market.setTransactionDate(LocalDateTime.now());

		// Rimuove il treno dalla vendita
		market.getTrenoInVendita().setInVendita(false);
		// Imposto il riferimento del nuovo propietario
		Treno t = market.getTrenoInVendita();
		t.setOwner(acquirente);

		// Aggiorna il treno e la transazione nel database
		trenoDao.update(t);
		marketDao.update(market);

		// Registra la transazione
		registraTransazione(market);
	}

	@Transactional
	public void registraTransazione(Transazione market) {
		if (market.getAcquirente() == null || market.getAmount() == null) {
			throw new IllegalArgumentException("Transazione incompleta. Manca l'acquirente o il prezzo.");
		}

		// Aggiunge il passaggio di proprietà del treno
		Treno treno = market.getTrenoInVendita();
		User venditore = market.getVenditore();
		User acquirente = market.getAcquirente();

		// Aggiorna lo stato del treno come non in vendita
		treno.setInVendita(false);
		trenoDao.update(treno);

		// Salva/aggiorna il market nel database (transazione completata)
		marketDao.update(market);

		System.out.println("Transazione registrata: " + venditore.getUsername() + " ha venduto un treno a "
				+ acquirente.getUsername() + " per €" + market.getAmount());
	}

	@Transactional
	public void rimuoviDallaVendita(Treno treno, User venditore) {
		// Controllo se il treno è in vendita
		if (!treno.getInVendita()) {
			throw new IllegalArgumentException("Il treno non è in vendita.");
		}

		// controllo se il venditore è lo stesso proprietario che ha messo in vendita il
		// treno
		Transazione market = ((TransazioneDao) marketDao).findTransactionByTreno(treno);
		if (market == null || !market.getVenditore().equals(venditore)) {
			throw new SecurityException("Non sei autorizzato a rimuovere questo treno dalla vendita.");
		}

		// tolgo il treno dalla vendita
		treno.setInVendita(false);
		trenoDao.update(treno);

		// tolgo l'inserimento di vendita dal database
		marketDao.delete(market);
	}

}
