package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Vagone;



public class VagoneDao extends ProxyDao<Vagone> {
	
    //Posso farlo anche cosi
	public VagoneDao() {
		super(Vagone.class);
	}
	
	@Transactional(readOnly = true)
    public List<Vagone> getVagoneByMarca(String marca) {
        String hql = "FROM Vagone v WHERE v.marca = :marca";
        return em.createQuery(hql, Vagone.class)
                 .setParameter("marca", marca)
                 .getResultList();
    }
	
	

}
