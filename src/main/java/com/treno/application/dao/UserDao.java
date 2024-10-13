package com.treno.application.dao;




import com.treno.application.model.User;

import jakarta.persistence.NoResultException;



public class UserDao extends ProxyDao<User> implements UserUtility{

	public UserDao() {
		super(User.class);
	}

	


	    @Override
	    public User findByUsername(String username) {
	        String hql = "FROM User u WHERE u.username = :username";
	        try {
	            return em.createQuery(hql, User.class)
	                     .setParameter("username", username)
	                     .getSingleResult();
	        } catch (NoResultException e) {
	            return null; // Nessun utente trovato con questo username
	        }

	
//	 public List<User> filtraUtenteByParametro(UtenteFilter filtro) {
//	        // Inizio della query HQL
//	        StringBuilder hql = new StringBuilder("FROM User u WHERE 1=1");
//        // Aggiunta dinamica di parametri di filtro
//	        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
//            hql.append(" AND u.nome = :nome");
//        }
//	        if (filtro.getCognome() != null && !filtro.getCognome().isEmpty()) {
//	            hql.append(" AND u.cognome = :cognome");
//	        }
//        if (filtro.getEmail() != null && !filtro.getEmail().isEmpty()) {
//	            hql.append(" AND u.email = :email");
//	        }
//        if (filtro.getEtà() != null) {
//	            hql.append(" AND u.età = :età");
//	        }
//
//	        // Creazione della query
//	        Query query = em.createQuery(hql.toString(), User.class);
//
//	        // Impostazione dei parametri in modo dinamico
//	        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
//	            query.setParameter("nome", filtro.getNome());
//	        }
//	        if (filtro.getCognome() != null && !filtro.getCognome().isEmpty()) {
//	            query.setParameter("cognome", filtro.getCognome());
//        }
//	        if (filtro.getEmail() != null && !filtro.getEmail().isEmpty()) {
//	            query.setParameter("email", filtro.getEmail());
//	        }
//	        if (filtro.getEtà() != null) {
//	            query.setParameter("età", filtro.getEtà());
//	        }
//	        // Restituisci la lista dei risultati
//	        return  query.getResultList();
//	    }
//	
	

	
}




}
