package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Valutazione;

public class ValutazioneDao extends ProxyDao<Valutazione> implements UtenteValutaTreno {

	public ValutazioneDao() {
		super(Valutazione.class);
	}
	
	 // Metodo che si occupa solo di inserire una nuova valutazione
	@Transactional
    public void inserisciValutazione(Valutazione valutazione) {
        super.update(valutazione);
    }
	
	//Media ranking treno
	public Double findMediaValutazioniByTreno(Long trenoId) {
	    String hql = "SELECT AVG(v.votazione) FROM Valutazione v WHERE v.treno.id = :trenoId";
	    return em.createQuery(hql, Double.class)
	             .setParameter("trenoId", trenoId)
	             .getSingleResult();
	}
	
	//tutte le valutazioni di un utente
	private List<Valutazione> supportoValutazioniByUser(Long userId) {
	    String hql = "FROM Valutazione v WHERE v.user.id = :userId";
	    return em.createQuery(hql, Valutazione.class)
	             .setParameter("userId", userId)
	             .getResultList();
	}

	@Override
	public List<Valutazione> findValutazioniByUser(long userId) {
		return this.supportoValutazioniByUser(userId);
	}
	
	

}
