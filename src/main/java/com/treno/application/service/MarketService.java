package com.treno.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.MarketDao;
import com.treno.application.dao.TrenoDao;
import com.treno.application.model.Market;
import com.treno.application.model.Treno;
import com.treno.application.model.User;

public class MarketService {

	@Autowired
	private MarketDao marketDao;
	
	
	@Autowired
	private TrenoDao trenoDao;
	
	
	@Autowired
	private Market market;

	public List<Treno> getAllTreniV() {
		return trenoDao.findAllV();
	}

	@Transactional
	
	public void mettereInVendita(Treno treno, User venditore) {
		// Controlla se il treno è già in vendita
		if (treno.getInVendita()) {
			throw new IllegalArgumentException("Il treno è già in vendita.");
		}

		// Crea transazione (transazione in corso) però vorrei gestire con un bean
		// vedremo.
		market.setTreniInVendita(treno);
		market.setVenditore(venditore);
		market.setTransactionDate(LocalDateTime.now()); // Data forse la toglierò ..

		// Imposta il treno come in vendita
		treno.setInVendita(true);
		trenoDao.update(treno); // Aggiorna lo stato del treno giustamente

		// Salva l'inserimento di vendita nel database tramite dao
		marketDao.save(market);
	}

	@Transactional
	public void acquistaTreno(Market market, User acquirente, double prezzo) {
		// Controlli di concorrenza e validità
		if (market == null || market.getTreniInVendita() == null) {
			throw new IllegalArgumentException("Transazione non valida. Il treno non esiste.");
		}

		if (market.getAcquirente() != null) {
			throw new IllegalStateException("Il treno è già stato acquistato da un altro utente.");
		}

		// Controlla se il treno è ancora in vendita
		if (!market.getTreniInVendita().getInVendita()) {
			throw new IllegalArgumentException("Il treno non è più disponibile per l'acquisto.");
		}

		// Registra l'acquirente, il prezzo e imposta la data della transazione
		market.setAcquirente(acquirente);
		market.setAmount(prezzo);
		market.setTransactionDate(LocalDateTime.now());

		// Rimuove il treno dalla vendita
		market.getTreniInVendita().setInVendita(false);

		// Aggiorna il treno e la transazione nel database
		trenoDao.update(market.getTreniInVendita());
		marketDao.update(market);

		// Registra la transazione
		registraTransazione(market);
	}

	@Transactional
	public void registraTransazione(Market market) {
		if (market.getAcquirente() == null || market.getAmount() == null) {
			throw new IllegalArgumentException("Transazione incompleta. Manca l'acquirente o il prezzo.");
		}

		// Aggiunge il passaggio di proprietà del treno
		Treno treno = market.getTreniInVendita();
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
		Market market = marketDao.findTransactionByTreno(treno);
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
