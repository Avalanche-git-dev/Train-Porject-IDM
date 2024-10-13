package com.treno.application.dao;

import java.util.List;

import com.treno.application.dto.TransazioneTotaleTrenoDTO;
import com.treno.application.model.Transazione;

public class TransazioneDao extends ProxyDao<Transazione> {

	public TransazioneDao() {
		super(Transazione.class);
	}
	
	public List<Transazione> findTransazioniByUser(int userId) {
	    String hql = "FROM Transazione t WHERE t.acquirente.id = :userId OR t.venditore.id = :userId";
	    return em.createQuery(hql, Transazione.class)
	             .setParameter("userId", userId)
	             .getResultList();
	}
	//amo HQL
	public List<Transazione> findTransazioniByTreno(int trenoId) {
	    String hql = "FROM Transazione t WHERE t.treno.id = :trenoId";
	    return em.createQuery(hql, Transazione.class)
	             .setParameter("trenoId", trenoId)
	             .getResultList();
	}
	
	//restituisce una lista di treni e ordinata in ordine decrescente per totale ammontare di transazioni (Tecnica DTo easy)
	
	public List<TransazioneTotaleTrenoDTO> findTreniByTotalTransactionValueDesc() {
	    String hql = "SELECT new com.treno.application.dto.TrenoTransazioneTotaleDTO(t.treno, SUM(t.importo)) " +
	                 "FROM Transazione t " +
	                 "GROUP BY t.treno " +
	                 "ORDER BY SUM(t.importo) DESC";
	    
	    return em.createQuery(hql, TransazioneTotaleTrenoDTO.class)
	             .getResultList();
	}
	
	
	

}
