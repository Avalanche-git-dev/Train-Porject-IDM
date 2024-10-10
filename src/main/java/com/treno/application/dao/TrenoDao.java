package com.treno.application.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component("trenoDao")
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
        return entityManager.createQuery("from treni", Treno.class).getResultList();
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
	
	@Transactional
	public List<Treno> findByFilter(TrenoFilter filter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Treno> criteriaQuery = criteriaBuilder.createQuery(Treno.class); 
		Root<Treno> criteriaRoot = criteriaQuery.from(Treno.class); // FROM Treno
		Predicate name = null, p1 = null, p2=null; 
		if (filter.getPrezzoMin() != 0) {
			p1  = criteriaBuilder.greaterThanOrEqualTo(criteriaRoot.get("prezzo"), filter.getPrezzoMin());
		}
		if (filter.getPrezzoMax() != 0) {
			p2  = criteriaBuilder.lessThanOrEqualTo(criteriaRoot.get("prezzo"), filter.getPrezzoMax());
		}
		// attenzione al bug dobbiamo fare i controlli sul predicato non nullo
		Predicate beetwenPrezzo = criteriaBuilder.and(p1,p2);
		Predicate finale= criteriaBuilder.and(name, beetwenPrezzo);
		criteriaQuery.where(finale);
		Query query = entityManager.createQuery(criteriaQuery);
		@SuppressWarnings("unchecked")
		List<Treno> result = query.getResultList();
		return result;
	}
	
	//Mi serve per il market scusate !
    public List<Treno> findAllV() {
        return entityManager.createQuery("FROM Treno t WHERE t.inVendita = true", Treno.class).getResultList();
    }

}
