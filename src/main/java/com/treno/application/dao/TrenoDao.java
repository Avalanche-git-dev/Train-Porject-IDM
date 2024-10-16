package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

import jakarta.persistence.Query;

public class TrenoDao extends ProxyDao<Treno> implements TrenoUtility {

	public TrenoDao() {
		super(Treno.class);
	}
     ///////////////////FIND

	public List<Treno> findAllInVendita() {
		String hql = "FROM Treno t WHERE t.InVendita = true";
		return super.em.createQuery(hql, Treno.class).getResultList();
	}
	
//    @Transactional
//	public List<Treno> findAllTreniByUser (long userId) {
//        String hql = "FROM Treno t WHERE t.owner.id = :userId";
//        return super.em.createQuery(hql, Treno.class)
//                 .setParameter("userId", userId)
//                 .getResultList();
//    }
	
	@Transactional
	public List<Treno> findAllTreniByUser(long userId) {
	    String hql = "SELECT DISTINCT t FROM Treno t " +
	                 "LEFT JOIN FETCH t.valutazioni " +   // Carica le valutazioni
	                 "LEFT JOIN FETCH t.transazioni " +   // Carica le transazioni
	                 "WHERE t.owner.id = :userId";
	    return super.em.createQuery(hql, Treno.class)
	                 .setParameter("userId", userId)
	                 .getResultList();
	}



	@Override
	public Treno findById(long trenoId) {
		return em.find(Treno.class, trenoId); // Trova l'entità Treno usando l'ID
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Treno> filtraTreni(TrenoFilter filtro, long userId) {
	    StringBuilder hql = new StringBuilder("FROM Treno t WHERE t.user.id = :userId");

	    
	    //Grazie prof per l'idea
	    if (filtro.getPrezzoMin() != null) {
	        hql.append(" AND t.prezzoVendita >= :prezzoMin");
	    }
	    if (filtro.getPrezzoMax() != null) {
	        hql.append(" AND t.prezzoVendita <= :prezzoMax");
	    }
	    if (filtro.getPesoMin() != null) {
	        hql.append(" AND t.peso >= :pesoMin");
	    }
	    if (filtro.getPesoMax() != null) {
	        hql.append(" AND t.peso <= :pesoMax");
	    }
	    if (filtro.getLunghezzaMin() != null) {
	        hql.append(" AND t.lunghezza >= :lunghezzaMin");
	    }
	    if (filtro.getLunghezzaMax() != null) {
	        hql.append(" AND t.lunghezza <= :lunghezzaMax");
	    }
	    if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
	        hql.append(" AND t.sigla = :sigla");
	    }
	    if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
	        hql.append(" AND t.marca = :marca");
	    }
	    if (filtro.getValutazioni() > 0) {
	        hql.append(" AND t.valutazioneTotale >= :valutazioni"); // Supponendo che ci sia un campo per la valutazione totale
	    }

	    Query query = super.em.createQuery(hql.toString());

	    // Impostazione dei parametri
	    query.setParameter("userId", userId);
	    
	    if (filtro.getPrezzoMin() != null) {
	        query.setParameter("prezzoMin", filtro.getPrezzoMin());
	    }
	    if (filtro.getPrezzoMax() != null) {
	        query.setParameter("prezzoMax", filtro.getPrezzoMax());
	    }
	    if (filtro.getPesoMin() != null) {
	        query.setParameter("pesoMin", filtro.getPesoMin());
	    }
	    if (filtro.getPesoMax() != null) {
	        query.setParameter("pesoMax", filtro.getPesoMax());
	    }
	    if (filtro.getLunghezzaMin() != null) {
	        query.setParameter("lunghezzaMin", filtro.getLunghezzaMin());
	    }
	    if (filtro.getLunghezzaMax() != null) {
	        query.setParameter("lunghezzaMax", filtro.getLunghezzaMax());
	    }
	    if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
	        query.setParameter("sigla", filtro.getSigla());
	    }
	    if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
	        query.setParameter("marca", filtro.getMarca());
	    }
	    if (filtro.getValutazioni() > 0) {
	        query.setParameter("valutazioni", filtro.getValutazioni());
	    }

	    return query.getResultList();
	}

}
