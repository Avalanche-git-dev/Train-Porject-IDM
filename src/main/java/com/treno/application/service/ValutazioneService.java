package com.treno.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.dto.ValutazioneDTO;
import com.treno.application.exception.ValutazioneException;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.Valutazione;
import com.treno.application.utility.UtenteValutaTreno;


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
		User user = userDao.findById(userId);
		if (user == null) {
			throw new Exception("Utente non trovato");
		}

		// Usa il metodo del DAO per ottenere tutte le valutazioni dell'utente
		return valutazioneDao.findValutazioniByUser(userId);
	}

	@Transactional
	public void UserValutaTreno(long userId, long trenoId, int voto) throws ValutazioneException {
		User user = userDao.findById(userId);
		Treno treno = trenoDao.findById(trenoId);

		if (user == null) {
			throw new ValutazioneException("Utente non trovato");
		}
		if (treno == null) {
			throw new ValutazioneException("Treno non trovato");
		}

		List<Valutazione> valutazioniUtente = valutazioneDao.findValutazioniByUser(userId);

		
		// Controlla se l'utente ha già valutato questo treno
		for (Valutazione v : valutazioniUtente) {
			if (v.getTreno().getIdTreno() == trenoId) {
				// Se l'utente ha già valutato questo treno, lancia un'eccezione o restituisci
				// un messaggio
				throw new ValutazioneException("L'utente ha già valutato questo treno e non può modificarlo.");
			}
		}

		Valutazione nuovaValutazione = new Valutazione();
		nuovaValutazione.setUser(user);
		nuovaValutazione.setTreno(treno);
		nuovaValutazione.setVotazione(voto);


		// Salva la nuova valutazione nel database
		valutazioneDao.update(nuovaValutazione);
	}
	
	
	
	//restituisce tutte le valutazioni di un treno
	public List<ValutazioneDTO> getAllValutazioniByTreno(Long idTreno) {
	    List<Valutazione> valutazioni =  valutazioneDao.findValutazioniByTreno(idTreno.longValue());
	    return valutazioni.stream()
	                      .map(this::convertToDTO) // Converti ogni entità in DTO
	                      .collect(Collectors.toList());
	}

	
	
	
	
	public ValutazioneDTO convertToDTO(Valutazione valutazione) {
	    ValutazioneDTO dto = new ValutazioneDTO();
	    dto.setIdValutazione(valutazione.getIdValutazione());
	    dto.setUserId(valutazione.getUser().getUserId());
	    dto.setUserNome(valutazione.getUser().getNome()); // Recupera il nome dell'utente
	    dto.setTrenoId(valutazione.getTreno().getIdTreno());
	    dto.setTrenoNome(valutazione.getTreno().getNome()); // Recupera il nome del treno
	    dto.setVotazione(valutazione.getVotazione());
	    return dto;
	}


}
