package com.treno.application.dao;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Passeggero;


@Component
public class PasseggeriDao implements Dao<Passeggero> {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
    public Passeggero findById(int id) {
        return entityManager.find(Passeggero.class, id);
    }

	@Override
    public List<Passeggero> findAll() {
        return entityManager.createQuery("from Passeggeri", Passeggero.class).getResultList();
    }

	@Override
    @Transactional
    public void save(Passeggero passeggeri) {
        entityManager.persist(passeggeri);
    }

	@Override
    @Transactional
    public void update(Passeggero passeggeri) {
        entityManager.merge(passeggeri);
    }

	@Override
    @Transactional
    public void delete(Passeggero passeggeri) {
        entityManager.remove(entityManager.contains(passeggeri) ? passeggeri : entityManager.merge(passeggeri));
    }

}