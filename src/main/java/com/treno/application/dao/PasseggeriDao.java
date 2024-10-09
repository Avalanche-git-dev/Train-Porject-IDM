package com.treno.application.dao;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Passeggeri;


@Component
public class PasseggeriDao implements Dao<Passeggeri> {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
    public Passeggeri findById(int id) {
        return entityManager.find(Passeggeri.class, id);
    }

	@Override
    public List<Passeggeri> findAll() {
        return entityManager.createQuery("from Passeggeri", Passeggeri.class).getResultList();
    }

	@Override
    @Transactional
    public void save(Passeggeri passeggeri) {
        entityManager.persist(passeggeri);
    }

	@Override
    @Transactional
    public void update(Passeggeri passeggeri) {
        entityManager.merge(passeggeri);
    }

	@Override
    @Transactional
    public void delete(Passeggeri passeggeri) {
        entityManager.remove(entityManager.contains(passeggeri) ? passeggeri : entityManager.merge(passeggeri));
    }

}