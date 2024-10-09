package com.treno.application.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Motrice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Component
public class MotriceDao implements Dao <Motrice> {
	
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Motrice findById(int id) {
        return entityManager.find(Motrice.class, id);
    }

    @Override
    public List<Motrice> findAll() {
        return entityManager.createQuery("from Motrice", Motrice.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Motrice motrice) {
        entityManager.persist(motrice);
    }

    @Override
    @Transactional
    public void update(Motrice motrice) {
        entityManager.merge(motrice);
    }

    @Override
    @Transactional
    public void delete(Motrice motrice) {
        entityManager.remove(entityManager.contains(motrice) ? motrice : entityManager.merge(motrice));
    }
	
		
	

}
