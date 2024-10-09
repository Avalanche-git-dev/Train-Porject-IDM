package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.Vagone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class VagoneDao implements Dao<Vagone> {

	    @PersistenceContext
	    private EntityManager entityManager;

	    @Override
	    public Vagone findById(int id) {
	        return entityManager.find(Vagone.class, id);
	    }

	    @Override
	    public List<Vagone> findAll() {
	        return entityManager.createQuery("from vagoni", Vagone.class).getResultList();
	    }

	    @Override
	    @Transactional
	    public void save(Vagone vagone) {
	        entityManager.persist(vagone);
	    }

	    @Override
	    @Transactional
	    public void update(Vagone vagone) {
	        entityManager.merge(vagone);
	    }

	    @Override
	    @Transactional
	    public void delete(Vagone vagone) {
	        entityManager.remove(entityManager.contains(vagone) ? vagone : entityManager.merge(vagone));
	    }
	}

//	@PersistenceContext
//	private EntityManager manager;
//
//	public EntityManager getManager() {
//		return manager;
//	}
//    @Transactional
//	@Override
//	public void create(Vagone vagone) {
//		vagone.setCosto(20);
//		vagone.setPeso(30);
//	    manager.persist(vagone);
//	    System.out.println("ho salvato sul db guardami cazzo.");
//
//	}
//
//	@Override
//	public void read() {
//
//	}
//
//	@Override
//	public void update() {
//
//	}
//
//	@Override
//	public void delete() {
//
//	}

	


