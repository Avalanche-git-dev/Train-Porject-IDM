package com.treno.application.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Cargo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Component
public class CargoDao implements Dao <Cargo> {

	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cargo findById(int id) {
        return entityManager.find(Cargo.class, id);
    }

    @Override
    public List<Cargo> findAll() {
        return entityManager.createQuery("from Cargo", Cargo.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Cargo cargo) {
        entityManager.persist(cargo);
    }

    @Override
    @Transactional
    public void update(Cargo cargo) {
        entityManager.merge(cargo);
    }

    @Override
    @Transactional
    public void delete(Cargo cargo) {
        entityManager.remove(entityManager.contains(cargo) ? cargo : entityManager.merge(cargo));
    }

}
