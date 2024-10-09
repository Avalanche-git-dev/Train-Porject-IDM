package com.treno.application.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Ristorante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Component
public class RistoranteDao implements Dao<Ristorante> {


	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ristorante findById(int id) {
        return entityManager.find(Ristorante.class, id);
    }

    @Override
    public List<Ristorante> findAll() {
        return entityManager.createQuery("from Ristorante", Ristorante.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Ristorante ristorante) {
        entityManager.persist(ristorante);
    }

    @Override
    @Transactional
    public void update(Ristorante ristorante) {
        entityManager.merge(ristorante);
    }

    @Override
    @Transactional
    public void delete(Ristorante ristorante) {
        entityManager.remove(entityManager.contains(ristorante) ? ristorante : entityManager.merge(ristorante));
    }
	

}
