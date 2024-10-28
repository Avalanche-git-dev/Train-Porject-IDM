package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Valutazione;
import com.treno.application.utility.UtenteValutaTreno;

//public class ValutazioneDao extends ProxyDao<Valutazione> implements UtenteValutaTreno {
//
//	public ValutazioneDao() {
//		super(Valutazione.class);
//	}
//	
//	 // Metodo che si occupa solo di inserire una nuova valutazione
//	@Transactional
//    public void inserisciValutazione(Valutazione valutazione) {
//        super.update(valutazione);
//    }
//	
//	//Media ranking treno
//	public Double findMediaValutazioniByTreno(Long trenoId) {
//	    String hql = "SELECT AVG(v.votazione) FROM Valutazione v WHERE v.treno.id = :trenoId";
//	    return em.createQuery(hql, Double.class)
//	             .setParameter("trenoId", trenoId)
//	             .getSingleResult();
//	}
//	
//	//tutte le valutazioni di un utente
//	private List<Valutazione> supportoValutazioniByUser(Long userId) {
//	    String hql = "FROM Valutazione v WHERE v.user.id = :userId";
//	    return em.createQuery(hql, Valutazione.class)
//	             .setParameter("userId", userId)
//	             .getResultList();
//	}
//
//	@Override
//	public List<Valutazione> findValutazioniByUser(long userId) {
//		return this.supportoValutazioniByUser(userId);
//	}
//	
//	
//	
//	
//	// Tutte le valutazioni di un treno
//	public List<Valutazione> findValutazioniByTreno(Long trenoId) {
//	    String hql = "FROM Valutazione v WHERE v.treno.id = :trenoId";
//	    return em.createQuery(hql, Valutazione.class)
//	             .setParameter("trenoId", trenoId)
//	             .getResultList();
//	}
//	
//	
//	
//	///ALLORAAAAA
//	@Override
//	public List<Valutazione> findValutazioniByTrenoAndUser(long trenoId, long userId) {
//	    String hql = "FROM Valutazione v WHERE v.treno.idTreno = :trenoId AND v.user.userId = :userId";
//	    return em.createQuery(hql, Valutazione.class)
//	             .setParameter("trenoId", trenoId)
//	             .setParameter("userId", userId)
//	             .getResultList();
//	}
//
//
//	
//
//}




public class ValutazioneDao extends ProxyDao<Valutazione> implements UtenteValutaTreno {

    public ValutazioneDao() {
        super(Valutazione.class);
    }

    // Metodo che si occupa solo di inserire una nuova valutazione
    @Transactional
    public void inserisciValutazione(Valutazione valutazione) {
        super.update(valutazione);
    }

    // Media ranking treno
    public Double findMediaValutazioniByTreno(Long trenoId) {
        String hql = "SELECT AVG(v.votazione) FROM Valutazione v WHERE v.treno.id = :trenoId";
        return em.createQuery(hql, Double.class)
                 .setParameter("trenoId", trenoId)
                 .getSingleResult();
    }

    // Tutte le valutazioni di un utente con DISTINCT e JOIN FETCH su User e Treno
    @Override
    public List<Valutazione> findValutazioniByUser(long userId) {
        String hql = "SELECT DISTINCT v FROM Valutazione v "
                   + "JOIN FETCH v.user "
                   + "JOIN FETCH v.treno "
                   + "WHERE v.user.id = :userId";
        return em.createQuery(hql, Valutazione.class)
                 .setParameter("userId", userId)
                 .getResultList();
    }

    // Tutte le valutazioni di un treno con DISTINCT e JOIN FETCH su User e Treno
    public List<Valutazione> findValutazioniByTreno(Long trenoId) {
        String hql = "SELECT DISTINCT v FROM Valutazione v "
                   + "JOIN FETCH v.user "
                   + "JOIN FETCH v.treno "
                   + "WHERE v.treno.id = :trenoId";
        return em.createQuery(hql, Valutazione.class)
                 .setParameter("trenoId", trenoId)
                 .getResultList();
    }

    // Tutte le valutazioni di un treno e utente specifico con DISTINCT e JOIN FETCH
    @Override
    public List<Valutazione> findValutazioniByTrenoAndUser(long trenoId, long userId) {
        String hql = "SELECT DISTINCT v FROM Valutazione v "
                   + "JOIN FETCH v.user "
                   + "JOIN FETCH v.treno "
                   + "WHERE v.treno.id = :trenoId AND v.user.id = :userId";
        return em.createQuery(hql, Valutazione.class)
                 .setParameter("trenoId", trenoId)
                 .setParameter("userId", userId)
                 .getResultList();
    }
}
