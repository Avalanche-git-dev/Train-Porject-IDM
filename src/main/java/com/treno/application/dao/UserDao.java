package com.treno.application.dao;

import java.util.ArrayList;
import java.util.List;

import com.treno.application.dto.UserDTO;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;
import com.treno.application.utility.UserUtility;

import jakarta.persistence.TypedQuery;


public class UserDao extends ProxyDao<User> implements UserUtility {

    public UserDao() {
        super(User.class);
    }


    public List<UserDTO> findAllUsers() {
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setNome(user.getNome());
            dto.setCognome(user.getCognome());
            dto.setTelefono(user.getTelefono());
            dto.setStato(user.getStato());
            dto.setPortafoglio(user.getPortafoglio());
            dto.setRuolo(user.getRuolo());
            userDTOs.add(dto);
        }
        return userDTOs;
    }

    
    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
    
    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public User findByPassword(String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.password = :password", User.class);
        query.setParameter("password", password);
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
        if (filtro.getNome() != null) {
            queryString.append(" AND u.nome LIKE :nome");
        }
        if (filtro.getCognome() != null) {
            queryString.append(" AND u.cognome LIKE :cognome");
        }
        if (filtro.getEtà() != null) {
            queryString.append(" AND u.età = :età");
        }
        if (filtro.getStato() != null) {
            queryString.append(" AND u.stato = :stato");
        }
        if (filtro.getTelefono() != null) {
            queryString.append(" AND u.telefono LIKE :telefono");
        }
        if (filtro.getPassword() != null) {
            queryString.append(" AND u.password = :password");
        }
        if (filtro.getNuovaPassword() != null) {
            queryString.append(" AND u.nuovaPassword = :nuovaPassword");
        }

        TypedQuery<User> query = em.createQuery(queryString.toString(), User.class);
        query.setParameter("userId", userId);

        if (filtro.getUsername() != null) {
            query.setParameter("username", "%" + filtro.getUsername() + "%");
        }
        if (filtro.getEmail() != null) {
            query.setParameter("email", "%" + filtro.getEmail() + "%");
        }
        if (filtro.getNome() != null) {
            query.setParameter("nome", "%" + filtro.getNome() + "%");
        }
        if (filtro.getCognome() != null) {
            query.setParameter("cognome", "%" + filtro.getCognome() + "%");
        }
        if (filtro.getEtà() != null) {
            query.setParameter("età", filtro.getEtà());
        }
        if (filtro.getStato() != null) {
            query.setParameter("stato", filtro.getStato());
        }
        if (filtro.getTelefono() != null) {
            query.setParameter("telefono", "%" + filtro.getTelefono() + "%");
        }
        if (filtro.getPassword() != null) {
            query.setParameter("password", filtro.getPassword());
        }
        if (filtro.getNuovaPassword() != null) {
            query.setParameter("nuovaPassword", filtro.getNuovaPassword());
        }

        return query.getResultList();
    }

    
    

}