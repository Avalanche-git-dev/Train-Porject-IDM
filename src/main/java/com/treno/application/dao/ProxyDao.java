package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ProxyDao<T> implements Dao<T> {
	
	@PersistenceContext
	protected EntityManager em;

	private Class<T> entityClass;

	public ProxyDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public T findById(long id) {
		return em.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		return em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
	}

	@Transactional
	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Transactional
	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}
}
