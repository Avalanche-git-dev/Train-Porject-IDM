package com.treno.application.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
@Component("transazioneDao")
public class TransazioneDao implements Dao<Transazione> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Transazione findById(int id) {
		return entityManager.find(Transazione.class, id);
	}

	@Override
	public List<Transazione> findAll() {
		return entityManager.createQuery("from Market", Transazione.class).getResultList();
	}

	@Transactional
	@Override
	public void save(Transazione market) {
		entityManager.persist(market);

	}

	@Transactional
	@Override
	public void update(Transazione market) {
		entityManager.merge(market);

	}

	@Transactional
	@Override
	public void delete(Transazione market) {
		entityManager.remove(entityManager.contains(market) ? market : entityManager.merge(market));

	}
	
	
	
	
	
	public Transazione findTransactionByTreno(Treno treno) {
        try {
            // Query JPQL per cercare il Market associato a questo treno
            return entityManager.createQuery(
                "SELECT m FROM Market m WHERE m.treniInVendita = :treno", Transazione.class)
                .setParameter("treno", treno)
                .getSingleResult();
        } catch (NoResultException e) {
            // Restituisce null se nessun market viene trovato per quel treno
            return null;
        }
    }
}


