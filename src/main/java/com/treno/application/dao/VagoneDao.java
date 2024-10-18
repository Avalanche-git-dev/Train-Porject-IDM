package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Vagone;

import jakarta.persistence.Query;



public class VagoneDao extends ProxyDao<Vagone> {
	
    //Posso farlo anche cosi
	public VagoneDao() {
		super(Vagone.class);
	}
	
	
	//getVagoniByMarca
	@Transactional(readOnly = true)
    public List<Vagone> getVagoneByMarca(String marca) {
        String hql = "FROM Vagone v WHERE v.marca = :marca";
        return em.createQuery(hql, Vagone.class)
                 .setParameter("marca", marca)
                 .getResultList();
    }

	//getVagoniByTreno
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Vagone> getVagonibyTreno(Long trenoId) {
	    String hql = "SELECT v FROM Vagone v WHERE v.treno.id = :trenoId";
	    Query query = em.createQuery(hql);
	    query.setParameter("trenoId", trenoId);
	    return (List<Vagone>)query.getResultList();
	}


	

}
