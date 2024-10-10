package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Market;
import com.treno.application.model.Treno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class MarketDao implements Dao<Market> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Market findById(int id) {
		return entityManager.find(Market.class, id);
	}

	@Override
	public List<Market> findAll() {
		return entityManager.createQuery("from Market", Market.class).getResultList();
	}

	@Transactional
	@Override
	public void save(Market market) {
		entityManager.persist(market);

	}

	@Transactional
	@Override
	public void update(Market market) {
		entityManager.merge(market);

	}

	@Transactional
	@Override
	public void delete(Market market) {
		entityManager.remove(entityManager.contains(market) ? market : entityManager.merge(market));

	}
	
	
	
	
	
	public Market findTransactionByTreno(Treno treno) {
        try {
            // Query JPQL per cercare il Market associato a questo treno
            return entityManager.createQuery(
                "SELECT m FROM Market m WHERE m.treniInVendita = :treno", Market.class)
                .setParameter("treno", treno)
                .getSingleResult();
        } catch (NoResultException e) {
            // Restituisce null se nessun market viene trovato per quel treno
            return null;
        }
    }
}


