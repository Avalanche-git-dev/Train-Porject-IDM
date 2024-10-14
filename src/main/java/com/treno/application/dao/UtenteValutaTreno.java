package com.treno.application.dao;

import java.util.List;

import com.treno.application.model.Valutazione;

public interface UtenteValutaTreno extends Dao<Valutazione> {
	
	List<Valutazione> findValutazioniByUser(long userId);
	public void inserisciValutazione(Valutazione valutazione);
	Double findMediaValutazioniByTreno(Long trenoId);
	
}
