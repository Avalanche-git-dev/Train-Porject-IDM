package com.treno.application.utility;

import java.util.List;

import com.treno.application.dao.Dao;
import com.treno.application.model.Valutazione;

public interface UtenteValutaTreno extends Dao<Valutazione> {
	
	List<Valutazione> findValutazioniByUser(long userId);
	public void inserisciValutazione(Valutazione valutazione);
	Double findMediaValutazioniByTreno(Long trenoId);
	List<Valutazione> findValutazioniByTreno(Long longValue);
	List<Valutazione> findValutazioniByTrenoAndUser(long trenoId, long userId);
	
}
