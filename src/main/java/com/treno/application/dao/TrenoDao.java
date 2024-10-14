package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

import jakarta.persistence.Query;

public class TrenoDao extends ProxyDao<Treno> implements TrenoUtility {

	public TrenoDao() {
		super(Treno.class);
	}
	
	public List<Treno> filtraTrenoByParametro(TrenoFilter filtro) {
	    StringBuilder hql = new StringBuilder("FROM Treno t WHERE 1=1");
	    if (filtro.getPrezzoMin() != null && filtro.getPrezzoMin() > 0) {
	        hql.append(" AND t.prezzoVendita >= :prezzoMin");
	    }
	    if (filtro.getPrezzoMax() != null && filtro.getPrezzoMax() > 0) {
	        hql.append(" AND t.prezzoVendita <= :prezzoMax");
	    }
	    if (filtro.getPesoMin() != null && filtro.getPesoMin() > 0) {
	        hql.append(" AND t.peso >= :pesoMin");
	    }
	    if (filtro.getPesoMax() != null && filtro.getPesoMax() > 0) {
	        hql.append(" AND t.peso <= :pesoMax");
	    }
	    if (filtro.getLunghezzaMin() != null && filtro.getLunghezzaMin() > 0) {
	        hql.append(" AND t.lunghezza >= :lunghezzaMin");
	    }
	    if (filtro.getLunghezzaMax() != null && filtro.getLunghezzaMax() > 0) {
	        hql.append(" AND t.lunghezza <= :lunghezzaMax");
	    }
	    if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
	        hql.append(" AND t.sigla LIKE :sigla");
	    }

	    Query query = em.createQuery(hql.toString(), Treno.class);
	    if (filtro.getPrezzoMin() != null && filtro.getPrezzoMin() > 0) {
	        query.setParameter("prezzoMin", filtro.getPrezzoMin());
	    }
	    if (filtro.getPrezzoMax() != null && filtro.getPrezzoMax() > 0) {
	        query.setParameter("prezzoMax", filtro.getPrezzoMax());
	    }
	    if (filtro.getPesoMin() != null && filtro.getPesoMin() > 0) {
	        query.setParameter("pesoMin", filtro.getPesoMin());
	    }
	    if (filtro.getPesoMax() != null && filtro.getPesoMax() > 0) {
	        query.setParameter("pesoMax", filtro.getPesoMax());
	    }
	    if (filtro.getLunghezzaMin() != null && filtro.getLunghezzaMin() > 0) {
	        query.setParameter("lunghezzaMin", filtro.getLunghezzaMin());
	    }
	    if (filtro.getLunghezzaMax() != null && filtro.getLunghezzaMax() > 0) {
	        query.setParameter("lunghezzaMax", filtro.getLunghezzaMax());
	    }
	    if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
	        query.setParameter("sigla", "%" + filtro.getSigla() + "%");
	    }
	    return query.getResultList();
	}


	public List<Treno> filterAllTreni(TrenoFilter filtro) {
		return null;
		}
	
	   public List<Treno> findAllInVendita() {
	        String hql = "FROM Treno t WHERE t.InVendita = true";
	        return super.em.createQuery(hql, Treno.class).getResultList();
	    }

	@Override
	public Treno findById(Treno treno) {
		return this.findById(treno.getIdTreno());
		
	}
	   
	   
}
