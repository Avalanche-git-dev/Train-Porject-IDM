package com.treno.application.dao;

import java.util.List;

import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;

import jakarta.persistence.TypedQuery;


public class UserDao extends ProxyDao<User> implements UserUtility {

    public UserDao() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<User> filtraUtenti(UtenteFilter filtro, long userId) {
        StringBuilder queryString = new StringBuilder("SELECT u FROM User u WHERE u.id <> :userId");

        if (filtro.getUsername() != null) {
            queryString.append(" AND u.username LIKE :username");
        }
        if (filtro.getEmail() != null) {
            queryString.append(" AND u.email LIKE :email");
        }
        // Aggiungere altri filtri se necessario

        TypedQuery<User> query = em.createQuery(queryString.toString(), User.class);
        query.setParameter("userId", userId);

        if (filtro.getUsername() != null) {
            query.setParameter("username", "%" + filtro.getUsername() + "%");
        }
        if (filtro.getEmail() != null) {
            query.setParameter("email", "%" + filtro.getEmail() + "%");
        }

        return query.getResultList();
    }
}