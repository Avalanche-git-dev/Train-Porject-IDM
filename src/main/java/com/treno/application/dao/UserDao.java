package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UserDao implements Dao<User> {

	@PersistenceContext
	private EntityManager entitytManager;

	@Override
	public User findById(int id) {
		return entitytManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return entitytManager.createQuery("from Utente", User.class).getResultList();
	}

	@Override
	@Transactional
	public void save(User user) {
		entitytManager.persist(user);

	}

	@Override
	@Transactional
	public void update(User user) {
		entitytManager.merge(user);

	}

	@Override
	@Transactional
	public void delete(User user) {
		entitytManager.remove(entitytManager.contains(user) ? user : entitytManager.merge(user));

	}

	/*
	 * void registration(User u); void login(User u); void logout(User u);
	 */

}