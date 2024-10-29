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
		 String hql = "SELECT DISTINCT t FROM Transazione t " +
                 "JOIN FETCH t.treno " +       // Aggiunge il fetch join per il treno
                 "JOIN FETCH t.acquirente " +  // Aggiunge il fetch join per l'acquirente
                 "JOIN FETCH t.venditore " +   // Aggiunge il fetch join per il venditore
                 "WHERE t.acquirente.id = :userId OR t.venditore.id = :userId";
    return em.createQuery(hql, Transazione.class)
             .setParameter("userId", userId)
             .getResultList();
	}

	
	
	// amo HQL
	public List<Transazione> findTransazioniByTreno(long trenoId) {
	    String hql = "SELECT DISTINCT t FROM Transazione t " +
	                 "JOIN FETCH t.acquirente " +
	                 "JOIN FETCH t.venditore " +
	                 "JOIN FETCH t.treno " +
	                 "WHERE t.treno.id = :trenoId";
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
	public List<Transazione> findTransazioniByVenditore(long venditoreId) {
	    // HQL per recuperare le transazioni dove l'utente è venditore, caricare le relazioni e ordinare per importo
	    String hql = "SELECT DISTINCT t FROM Transazione t " +
	                 "LEFT JOIN FETCH t.treno " +
	                 "LEFT JOIN FETCH t.acquirente " +
	                 "LEFT JOIN FETCH t.venditore " +
	                 "WHERE t.venditore.userId = :venditoreId " +
	                 "ORDER BY t.importo DESC";

	    return em.createQuery(hql, Transazione.class)
	             .setParameter("venditoreId", venditoreId)
	             .getResultList();
	}

	@Transactional
	public List<Transazione> findTransazioniByAcquirente(long acquirenteId) {
	    // HQL per recuperare le transazioni dove l'utente è acquirente, caricare le relazioni e ordinare per importo
	    String hql = "SELECT DISTINCT t FROM Transazione t " +
	                 "LEFT JOIN FETCH t.treno " +
	                 "LEFT JOIN FETCH t.acquirente " +
	                 "LEFT JOIN FETCH t.venditore " +
	                 "WHERE t.acquirente.userId = :acquirenteId " +
	                 "ORDER BY t.importo DESC";

	    return em.createQuery(hql, Transazione.class)
	             .setParameter("acquirenteId", acquirenteId)
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
