package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

import jakarta.persistence.TypedQuery;

public class TrenoDao extends ProxyDao<Treno> implements TrenoUtility {

	public TrenoDao() {
		super(Treno.class);
	}

	public List<Treno> filterAllTreni(TrenoFilter filtro) {
		return null;
	}

	public List<Treno> findAllInVendita() {
		String hql = "FROM Treno t WHERE t.InVendita = true";
		return super.em.createQuery(hql, Treno.class).getResultList();
	}

	public List<Treno> findAllTreniByUser (long userId) {
        String hql = "FROM Treno t WHERE t.owner.id = :userId";
        return em.createQuery(hql, Treno.class)
                 .setParameter("userId", userId)
                 .getResultList();
    }
//
//	public List<Treno> findAllTreniByUser(long userId) {
//		String hql = "FROM Treno t WHERE t.user.id = :userId";
//		TypedQuery<Treno> query = em.createQuery(hql, Treno.class);
//		query.setParameter("userId", userId);
//		return query.getResultList();
//	}

	@Override
	public Treno findById(long trenoId) {
		return em.find(Treno.class, trenoId); // Trova l'entità Treno usando l'ID
	}

	public List<Treno> filtraTreni(TrenoFilter filtro, long userId) {
		String hql = "FROM Treno t WHERE t.owner.id = :userId";

		// Aggiungiamo i filtri solo se non sono null o validi
		if (filtro.getPrezzoMin() != null) {
			hql += " AND t.prezzo >= :prezzoMin";
		}
		if (filtro.getPrezzoMax() != null) {
			hql += " AND t.prezzo <= :prezzoMax";
		}
		if (filtro.getPesoMin() != null) {
			hql += " AND t.peso >= :pesoMin";
		}
		if (filtro.getPesoMax() != null) {
			hql += " AND t.peso <= :pesoMax";
		}
		if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
			hql += " AND t.marca = :marca";
		}

		TypedQuery<Treno> query = em.createQuery(hql, Treno.class);

		// Impostiamo il parametro userId
		query.setParameter("userId", userId);

		// Impostiamo i parametri solo se i filtri non sono null
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
		if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
			query.setParameter("marca", filtro.getMarca());
		}

		return query.getResultList();
	}

}
