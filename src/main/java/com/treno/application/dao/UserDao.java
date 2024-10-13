package com.treno.application.dao;




import java.util.List;

import com.treno.application.filter.UtenteFilter;
import com.treno.application.model.User;

import jakarta.persistence.Query;


<<<<<<< HEAD
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findById(int id) {
		return entityManager.find(User.class, id);
=======

public class UserDao extends ProxyDao<User>{

	public UserDao() {
		super(User.class);
>>>>>>> 01e3b02e6968efe5195021a1abc20046c2ac48d4
	}
	
	 public List<User> filtraUtenteByParametro(UtenteFilter filtro) {
	        // Inizio della query HQL
	        StringBuilder hql = new StringBuilder("FROM User u WHERE 1=1");
        // Aggiunta dinamica di parametri di filtro
	        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            hql.append(" AND u.nome = :nome");
        }
	        if (filtro.getCognome() != null && !filtro.getCognome().isEmpty()) {
	            hql.append(" AND u.cognome = :cognome");
	        }
        if (filtro.getEmail() != null && !filtro.getEmail().isEmpty()) {
	            hql.append(" AND u.email = :email");
	        }
        if (filtro.getEtà() != null) {
	            hql.append(" AND u.età = :età");
	        }

<<<<<<< HEAD
	@Override
	public List<User> findAll() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	@Override
	@Transactional
	public void save(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
	}

}
=======
	        // Creazione della query
	        Query query = em.createQuery(hql.toString(), User.class);

	        // Impostazione dei parametri in modo dinamico
	        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
	            query.setParameter("nome", filtro.getNome());
	        }
	        if (filtro.getCognome() != null && !filtro.getCognome().isEmpty()) {
	            query.setParameter("cognome", filtro.getCognome());
        }
	        if (filtro.getEmail() != null && !filtro.getEmail().isEmpty()) {
	            query.setParameter("email", filtro.getEmail());
	        }
	        if (filtro.getEtà() != null) {
	            query.setParameter("età", filtro.getEtà());
	        }
	        // Restituisci la lista dei risultati
	        return  query.getResultList();
	    }
	
	

	
}
>>>>>>> 01e3b02e6968efe5195021a1abc20046c2ac48d4
