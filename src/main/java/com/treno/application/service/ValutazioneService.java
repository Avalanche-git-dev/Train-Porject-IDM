package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dao.UtenteValutaTreno;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.Valutazione;


public class ValutazioneService {

	@Autowired
	@Qualifier("ValutazioneDao")
	private UtenteValutaTreno valutazioneDao;

	@Autowired
	@Qualifier("UserDao")
	private Dao<User> userDao;

	@Autowired
	@Qualifier("TrenoDao")
	private Dao<Treno> trenoDao;

	// Media delle valutazioni di un treno
	public Double mediaValutazioniPerTreno(Long trenoId) throws Exception {
		Treno treno = trenoDao.findById(trenoId);
		if (treno == null) {
			throw new Exception("Treno non trovato");
		}

		// Usa il metodo del DAO per calcolare la media delle valutazioni
		return valutazioneDao.findMediaValutazioniByTreno(trenoId);
	}

	// Ottenere tutte le valutazioni di un utente
	public List<Valutazione> getValutazioniPerUtente(Long userId) throws Exception {
		User user = userDao.findById(userId.intValue());
		if (user == null) {
			throw new Exception("Utente non trovato");
		}

		// Usa il metodo del DAO per ottenere tutte le valutazioni dell'utente
		return valutazioneDao.findValutazioniByUser(userId);
	}

	@Transactional
	public void UserValutaTreno(long userId, long trenoId, int voto) throws Exception {
		User user = userDao.findById(userId);
		Treno treno = trenoDao.findById(trenoId);

		if (user == null) {
			throw new Exception("Utente non trovato");
		}
		if (treno == null) {
			throw new Exception("Treno non trovato");
		}

		List<Valutazione> valutazioniUtente = valutazioneDao.findValutazioniByUser(userId);

		
		// Controlla se l'utente ha già valutato questo treno
		for (Valutazione v : valutazioniUtente) {
			if (v.getTreno().getIdTreno() == trenoId) {
				// Se l'utente ha già valutato questo treno, lancia un'eccezione o restituisci
				// un messaggio
				throw new Exception("L'utente ha già valutato questo treno e non può modificarlo.");
			}
		}

		Valutazione nuovaValutazione = new Valutazione();
		nuovaValutazione.setUser(user);
		nuovaValutazione.setTreno(treno);
		nuovaValutazione.setVotazione(voto);


		// Salva la nuova valutazione nel database
		valutazioneDao.update(nuovaValutazione);
	}

}
