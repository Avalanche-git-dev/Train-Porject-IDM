package com.treno.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.UserDTO;
import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.Admin;
import com.treno.application.model.User;
import com.treno.application.model.User.Stato;
import com.treno.application.utility.UserUtility;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


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


	
	
	
	// Filtro admin
	@Override
	public List<User> filtraUtenti(UtenteFilter filtro) {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    Root<User> userRoot = cq.from(User.class);

	    userRoot.fetch("stato", JoinType.LEFT);
	    userRoot.fetch("treni", JoinType.LEFT);
	    userRoot.fetch("valutazioni", JoinType.LEFT);
	    userRoot.fetch("transazioni", JoinType.LEFT);

	    cq.select(userRoot).distinct(true);

	    List<Predicate> predicates = new ArrayList<>();

	    if (filtro.getUsername() != null) {
	        predicates.add(cb.equal(userRoot.get("username"), filtro.getUsername()));
	    }
	    if (filtro.getPassword() != null) {
	        predicates.add(cb.equal(userRoot.get("password"), filtro.getPassword()));
	    }
	    if (filtro.getStato() != null) {
	        predicates.add(cb.equal(userRoot.get("stato"), filtro.getStato()));
	    }
	    if (filtro.getNumeroTreni() != 0) {
	        predicates.add(cb.greaterThanOrEqualTo(cb.size(userRoot.get("treni")), filtro.getNumeroTreni()));
	    }
	    if (filtro.getNumeroValutazioni() != 0) {
	        predicates.add(cb.greaterThanOrEqualTo(cb.size(userRoot.get("valutazioni")), filtro.getNumeroValutazioni()));
	    }
	    if (filtro.getNumeroTransazioni() != 0) {
	        predicates.add(cb.greaterThanOrEqualTo(cb.size(userRoot.get("transazioni")), filtro.getNumeroTransazioni()));
	    }

	    // Applica i predicati alla query
	    if (!predicates.isEmpty()) {
	        cq.where(predicates.toArray(new Predicate[0]));
	    }

	    TypedQuery<User> query = em.createQuery(cq);
	    return query.getResultList();
	}

	
	
	
	// findAllAdmin
	@Transactional
	public List<Admin> findAllAdminWithPrivileges() {
	    String hql = "SELECT DISTINCT a FROM Admin a " +
	                 "LEFT JOIN FETCH a.treni t " +
	                 "LEFT JOIN FETCH t.valutazioni " +
	                 "LEFT JOIN FETCH t.transazioni " +
	                 "WHERE a.privilegio = true";
	    
	    return super.em.createQuery(hql, Admin.class).getResultList();
	}

	
	@Transactional
	public List<User> findAllLockedUsers() {
	    String hql = "SELECT u FROM User u WHERE u.stato = :stato";
	    return em.createQuery(hql, User.class)
	             .setParameter("stato", Stato.locked)
	             .getResultList();
	}
	
	// findAllAttivi
	@Transactional
	public List<User> findAllActiveUsers() {
	    // HQL per trovare tutti gli utenti attivi (stato unlocked) e caricare le relazioni
	    String hql = "SELECT DISTINCT u FROM User u " +
	                 "LEFT JOIN FETCH u.treni t " +
	                 "LEFT JOIN FETCH t.valutazioni " +
	                 "LEFT JOIN FETCH t.transazioni " +
	                 "WHERE u.stato = :stato";
	    
	    return super.em.createQuery(hql, User.class)
	                   .setParameter("stato", Stato.unlocked)  // Passiamo l'enum Stato.unlocked come parametro
	                   .getResultList();
	}
//	
//	@Override
//	public Admin findAdminByUserId(Long userId) {
//		String hql = "SELECT a FROM Admin a WHERE a.userId = :userId";
//	    return em.createQuery(hql, Admin.class)
//	             .setParameter("userId", userId)
//	             .getSingleResult();
//		
//		
//	}
	
	
	
//	@Override
//	public Admin findAdminByUserId(Long userId) {
//	    String hql = "SELECT a FROM Admin a WHERE a.userId = :userId";
//	    try {
//	        return em.createQuery(hql, Admin.class)
//	                 .setParameter("userId", userId)
//	                 .getSingleResult();
//	    } catch (UserNotFoundException e) {
//	        throw new AdminNotFoundException("Amministratore non trovato con ID: " + userId);
//	    }
//	}
	
	
	@Override
	public Admin findAdminByUserId(Long userId) {
	    String hql = "SELECT a FROM Admin a WHERE a.userId = :userId";
	    List<Admin> result = em.createQuery(hql, Admin.class)
	                           .setParameter("userId", userId)
	                           .getResultList();
	    
	    if (result.isEmpty()) {
	        return null;  // Oppure puoi lanciare una tua eccezione personalizzata
	    }
	    
	    return result.get(0);  // Ritorna il primo risultato
	}



}