package com.treno.application.dao;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Passeggeri;
import com.treno.application.model.Treno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TrenoDao implements Dao<Treno> {
	
	/*
	void save(Treno train);
    
    List<Treno> showByPrice(double price);
    List<Treno> showByWeight(double weigth);
    List<Treno> showByLength(double length);
    
    void updateTrain(int id);
    void deleteTrain(int id);
    void duplicateTrain(int id);
    void reverseTrain(int id);
    
	
	Treno findById(int id);
	List<Treno> showAll();
	List<Treno> findByUser(User user);
	List<Treno> findBySigla(String sigla);
	*/
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
    public Treno findById(int id) {
        return entityManager.find(Treno.class, id);
    }

	@Override
    public List<Treno> findAll() {
        return entityManager.createQuery("from Treno", Treno.class).getResultList();
    }

	@Override
    @Transactional
    public void save(Treno treno) {
        entityManager.persist(treno);
    }

	@Override
    @Transactional
    public void update(Treno treno) {
        entityManager.merge(treno);
    }

	@Override
    @Transactional
    public void delete(Treno treno) {
        entityManager.remove(entityManager.contains(treno) ? treno : entityManager.merge(treno));
    }

}
