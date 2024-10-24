package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.TransazioneDTO;
import com.treno.application.model.Transazione;
import com.treno.application.utility.TransazioneUtility;

public class TransazioneDao extends ProxyDao<Transazione> implements TransazioneUtility {

	public TransazioneDao() {
		super(Transazione.class);
	}
	
	public List<Transazione> findTransazioniByUser(long userId) {
	    String hql = "FROM Transazione t WHERE t.acquirente.id = :userId OR t.venditore.id = :userId";
	    return em.createQuery(hql, Transazione.class)
	             .setParameter("userId", userId)
	             .getResultList();
	}
	//amo HQL
	public List<Transazione> findTransazioniByTreno(long trenoId) {
	    String hql = "FROM Transazione t WHERE t.treno.id = :trenoId";
	    return em.createQuery(hql, Transazione.class)
	             .setParameter("trenoId", trenoId)
	             .getResultList();
	}
	
	//restituisce una lista di treni e ordinata in ordine decrescente per totale ammontare di transazioni (Tecnica DTo easy)
	
	public List<TransazioneDTO> findTreniByTotalTransactionValueDesc() {
	    String hql = "SELECT new com.treno.application.dto.TrenoTransazioneTotaleDTO(t.treno, SUM(t.importo)) " +
	                 "FROM Transazione t " +
	                 "GROUP BY t.treno " +
	                 "ORDER BY SUM(t.importo) DESC";
	    
	    return em.createQuery(hql, TransazioneDTO.class)
	             .getResultList();
	}
	
	@Transactional
	public List<Transazione> findAllTransazioniOrdinatePerImporto() {
	    // HQL per recuperare tutte le transazioni, caricare le relazioni e ordinarle per importo decrescente
	    String hql = "SELECT DISTINCT t FROM Transazione t " +
	                 "LEFT JOIN FETCH t.treno " +
	                 "LEFT JOIN FETCH t.acquirente " +
	                 "LEFT JOIN FETCH t.venditore " +
	                 "ORDER BY t.importo DESC";

	    return em.createQuery(hql, Transazione.class)
	             .getResultList();
	}

	
	
	@Transactional
	public List<Transazione> findAllTransazioni() {
	    // HQL per recuperare tutte le transazioni con join fetch per evitare il problema del Lazy Loading
	    String hql = "SELECT DISTINCT t FROM Transazione t " +
	                 "LEFT JOIN FETCH t.treno " +
	                 "LEFT JOIN FETCH t.acquirente " +
	                 "LEFT JOIN FETCH t.venditore " +
	                 "ORDER BY t.importo DESC";

	    return super.em.createQuery(hql, Transazione.class)
	                   .getResultList();
	}
//	
	@Transactional
	public List<Transazione> findTransazioniOrdinatePerDataRecente() {
	    // HQL per recuperare tutte le transazioni e ordinarle per data (dalla più recente alla meno recente)
	    String hql = "SELECT t FROM Transazione t " +
	                 "LEFT JOIN FETCH t.treno " +
	                 "LEFT JOIN FETCH t.acquirente " +
	                 "LEFT JOIN FETCH t.venditore " +
	                 "ORDER BY t.data DESC";  // Ordina per data in ordine decrescente (dalla più recente)
	    
	    return em.createQuery(hql, Transazione.class)
	             .getResultList();
	}
	
	
//	@Transactional
//	public List<Transazione> findTransazioniOrdinatePerDataRecente() {
//	    // HQL per recuperare tutte le transazioni ordinate per data (dalla più recente alla meno recente)
//	    String hql = "FROM Transazione t ORDER BY t.data DESC";  // Ordina per data in ordine decrescente
//	    
//	    return em.createQuery(hql, Transazione.class)
//	             .getResultList();
//	}





	
	

}
