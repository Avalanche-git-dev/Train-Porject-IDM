package com.treno.application.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.Vagone;
import com.treno.application.utility.TrenoUtility;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TrenoDao extends ProxyDao<Treno> implements TrenoUtility {

    public TrenoDao() {
        super(Treno.class);
    }

    /////////////////// FIND
    //Override
    @Transactional
    @Override
    public Treno findById(long id) {
        String hql = "FROM Treno t LEFT JOIN FETCH t.valutazioni LEFT JOIN FETCH t.vagoni LEFT JOIN FETCH t.transazioni WHERE t.idTreno = :id";
        return em.createQuery(hql, Treno.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }
    
    @Transactional
    @Override
    public TrenoDTO findByTrenoId(long id) {
        String hql = "FROM Treno t LEFT JOIN FETCH t.valutazioni LEFT JOIN FETCH t.vagoni LEFT JOIN FETCH t.transazioni WHERE t.idTreno = :id";
        // Recupera l'oggetto Treno
        Treno treno = em.createQuery(hql, Treno.class)
                        .setParameter("id", id)
                        .getSingleResult();
        // Stampa l'oggetto Treno per verificare i dati
        System.out.println("Oggetto Treno: " + treno);
        return em.createQuery(hql, TrenoDTO.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }

    @Transactional
    public List<Treno> findAllInVendita() {
        // HQL con JOIN FETCH per caricare anche valutazioni e transazioni dei treni in vendita
    	String hql = "SELECT DISTINCT t FROM Treno t " +
    				 "LEFT JOIN FETCH t.valutazioni " +
    				 "LEFT JOIN FETCH t.transazioni " +
    				 "WHERE t.inVendita = true";
        
        // Esegui la query e restituisci la lista di treni in vendita
        return super.em.createQuery(hql, Treno.class).getResultList();
    }
    
    @Transactional
    public List<Treno> findByOwnerIdAndInVenditaFalse(Long ownerId) {
        String hql = "SELECT DISTINCT t FROM Treno t " +
                     "LEFT JOIN FETCH t.valutazioni " +
                     "LEFT JOIN FETCH t.transazioni " +
                     "WHERE t.owner.id = :ownerId " +
                     "AND t.inVendita = false";        
        // Esegui la query e restituisci la lista di treni che non sono in vendita
        return super.em.createQuery(hql, Treno.class)
                       .setParameter("ownerId", ownerId)
                       .getResultList();
    }




    @Transactional
    public List<Treno> findAllTreniByUser(long userId) {
        String hql = "SELECT DISTINCT t FROM Treno t " +
                     "LEFT JOIN FETCH t.valutazioni " +   // Carica le valutazioni
                     "LEFT JOIN FETCH t.transazioni " +   // Carica le transazioni
                     "WHERE t.owner.id = :userId";
        return super.em.createQuery(hql, Treno.class)
                     .setParameter("userId", userId)
                     .getResultList();
    }
    
    /*
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Treno> filtraTreni(TrenoFilter filtro) {
        // Creazione della query HQL con JOIN FETCH per valutazioni e transazioni
        StringBuilder hql = new StringBuilder("SELECT DISTINCT t FROM Treno t ");
        hql.append("LEFT JOIN FETCH t.valutazioni v ");
        hql.append("LEFT JOIN FETCH t.transazioni tr ");
        hql.append("WHERE 1=1");

        // Aggiunta dinamica delle condizioni di filtro
        if (filtro.getPrezzoMin() != null) {
            hql.append(" AND t.prezzoVendita >= :prezzoMin");
        }
        if (filtro.getPrezzoMax() != null) {
            hql.append(" AND t.prezzoVendita <= :prezzoMax");
        }
        if (filtro.getPesoMin() != null) {
            hql.append(" AND t.peso >= :pesoMin");
        }
        if (filtro.getPesoMax() != null) {
            hql.append(" AND t.peso <= :pesoMax");
        }
        if (filtro.getLunghezzaMin() != null) {
            hql.append(" AND t.lunghezza >= :lunghezzaMin");
        }
        if (filtro.getLunghezzaMax() != null) {
            hql.append(" AND t.lunghezza <= :lunghezzaMax");
        }
        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
            hql.append(" AND t.sigla LIKE :sigla"); // Cambiato a LIKE per corrispondenza parziale
        }
        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
            hql.append(" AND t.marca = :marca");
        }
        if (filtro.getValutazioni() != null && filtro.getValutazioni() > 0) {
            hql.append(" AND t.valutazioneTotale >= :valutazioni");
        }
        if (filtro.getPrezzoVendita() != null) {
            hql.append(" AND t.prezzoVendita = :prezzoVendita");
        }
        if (filtro.getAmmontareTotale() != null) {
            hql.append(" AND t.ammontareTotale = :ammontareTotale");
        }

        // Creazione della query
        Query query = em.createQuery(hql.toString());

        // Impostazione dei parametri
        if (filtro.getPrezzoMin() != null) {
            query.setParameter("prezzoMin", filtro.getPrezzoMin());
        }
        if (filtro.getPrezzoMax() != null) {
            query.setParameter("prezzoMax", filtro.getPrezzoMax());
        }
        if (filtro.getPesoMin() != null) {
            query.setParameter("pesoMin", filtro.getPesoMin());
        }
        if (filtro.getPesoMax() != null) {
            query.setParameter("pesoMax", filtro.getPesoMax());
        }
        if (filtro.getLunghezzaMin() != null) {
            query.setParameter("lunghezzaMin", filtro.getLunghezzaMin());
        }
        if (filtro.getLunghezzaMax() != null) {
            query.setParameter("lunghezzaMax", filtro.getLunghezzaMax());
        }
        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
            query.setParameter("sigla", "%" + filtro.getSigla() + "%"); // Aggiunto jolly per corrispondenza parziale
        }
        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
            query.setParameter("marca", filtro.getMarca());
        }
        if (filtro.getValutazioni() != null && filtro.getValutazioni() > 0) {
            query.setParameter("valutazioni", filtro.getValutazioni());
        }
        if (filtro.getPrezzoVendita() != null) {
            query.setParameter("prezzoVendita", filtro.getPrezzoVendita());
        }
        if (filtro.getAmmontareTotale() != null) {
            query.setParameter("ammontareTotale", filtro.getAmmontareTotale());
        }

        System.out.println("HQL Query: " + hql.toString()); // Stampa la query per il debug

        return query.getResultList();
    }
    */
    
    @Transactional
    public List<Treno> filtraTreni(TrenoFilter filtro) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);
        Root<Treno> treno = cq.from(Treno.class);
        treno.fetch("valutazioni", JoinType.LEFT);
        treno.fetch("transazioni", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        // Filtro per nome
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
        	predicates.add(cb.like(treno.get("nome"), "%" + filtro.getNome() + "%"));
        }
        // Filtro per sigla
        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
            predicates.add(cb.like(treno.get("sigla"), "%" + filtro.getSigla() + "%"));
        }
        // Filtro per marca
        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
        	predicates.add(cb.equal(treno.get("marca"), filtro.getMarca()));
        }
		// Filtro per peso 
        if (filtro.getPesoMin() != null && filtro.getPesoMax() !=null) { 
        	predicates.add(cb.between(treno.get("pesoTotale"), filtro.getPesoMin(), filtro.getPesoMax())); 
        } else if (filtro.getPesoMin() != null) { 
        	predicates.add(cb.ge(treno.get("pesoTotale"), filtro.getPesoMin()));
		} else if (filtro.getPesoMax() != null) {
			predicates.add(cb.le(treno.get("pesoTotale"), filtro.getPesoMax())); 
		}
	    // Filtro per Lunghezza 
        if (filtro.getLunghezzaMin() != null && filtro.getLunghezzaMax() != null) {
		  predicates.add(cb.between(treno.get("lunghezzaTotale"), filtro.getLunghezzaMin(), filtro.getLunghezzaMax())); 
		} else if (filtro.getLunghezzaMin() != null) {
		  predicates.add(cb.ge(treno.get("lunghezzaTotale"), filtro.getLunghezzaMin())); 
		} else if (filtro.getLunghezzaMax() != null) {
		  predicates.add(cb.le(treno.get("lunghezzaTotale"), filtro.getLunghezzaMax())); 
		} 
        // Filtro per Valutazioni
        if (filtro.getValutazioneMin() != null && filtro.getValutazioneMax() != null) {
        	predicates.add(cb.between(treno.get("valutazioneMedia"), filtro.getValutazioneMin(), filtro.getValutazioneMax()));
        } else if (filtro.getValutazioneMin() != null) {
        	predicates.add(cb.ge(treno.get("valutazioneMedia"), filtro.getValutazioneMin()));
    	} else if (filtro.getValutazioneMax() != null) {
    		predicates.add(cb.le(treno.get("valutazioneMedia"), filtro.getValutazioneMax()));
    	}
        
        cq.select(treno).distinct(true).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Treno> query = em.createQuery(cq);
        return query.getResultList();
    }



    
    
    
    @Override
    @Transactional
    public List<Treno> findAll() {
        // HQL con JOIN FETCH per caricare anche le valutazioni e transazioni
        String hql = "SELECT DISTINCT t FROM Treno t " +
                     "LEFT JOIN FETCH t.valutazioni " +
                     "LEFT JOIN FETCH t.transazioni";
        
        // Esegui la query e restituisci la lista di treni
        return super.em.createQuery(hql, Treno.class).getResultList();
    }
    
    
    
	
	
	//getVagoniByTreno
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Vagone> getVagonibyTreno(Long trenoId) {
	    String hql = "SELECT v FROM Vagone v WHERE v.treno.id = :trenoId";
	    Query query = em.createQuery(hql);
	    query.setParameter("trenoId", trenoId);
	    return (List<Vagone>)query.getResultList();
	}

	@Transactional
    public List<Treno> filtraTreniInVendita(TrenoFilter filtro) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);
        Root<Treno> treno = cq.from(Treno.class);
        treno.fetch("valutazioni", JoinType.LEFT);
        treno.fetch("transazioni", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();
        // Filtro per nome
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
        	predicates.add(cb.like(treno.get("nome"), "%" + filtro.getNome() + "%"));
        }
        // Filtro per sigla
        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
            predicates.add(cb.like(treno.get("sigla"), "%" + filtro.getSigla() + "%"));
        }
        // Filtro per marca
        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
        	predicates.add(cb.equal(treno.get("marca"), filtro.getMarca()));
        }
		// Filtro per peso 
        if (filtro.getPesoMin() != null && filtro.getPesoMax() !=null) { 
        	predicates.add(cb.between(treno.get("pesoTotale"), filtro.getPesoMin(), filtro.getPesoMax())); 
        } else if (filtro.getPesoMin() != null) { 
        	predicates.add(cb.ge(treno.get("pesoTotale"), filtro.getPesoMin()));
		} else if (filtro.getPesoMax() != null) {
			predicates.add(cb.le(treno.get("pesoTotale"), filtro.getPesoMax())); 
		}
	    // Filtro per Lunghezza 
        if (filtro.getLunghezzaMin() != null && filtro.getLunghezzaMax() != null) {
		  predicates.add(cb.between(treno.get("lunghezzaTotale"), filtro.getLunghezzaMin(), filtro.getLunghezzaMax())); 
		} else if (filtro.getLunghezzaMin() != null) {
		  predicates.add(cb.ge(treno.get("lunghezzaTotale"), filtro.getLunghezzaMin())); 
		} else if (filtro.getLunghezzaMax() != null) {
		  predicates.add(cb.le(treno.get("lunghezzaTotale"), filtro.getLunghezzaMax())); 
		} 
        // Filtro per Valutazioni
        if (filtro.getValutazioneMin() != null && filtro.getValutazioneMax() != null) {
        	predicates.add(cb.between(treno.get("valutazioneMedia"), filtro.getValutazioneMin(), filtro.getValutazioneMax()));
        } else if (filtro.getValutazioneMin() != null) {
        	predicates.add(cb.ge(treno.get("valutazioneMedia"), filtro.getValutazioneMin()));
    	} else if (filtro.getValutazioneMax() != null) {
    		predicates.add(cb.le(treno.get("valutazioneMedia"), filtro.getValutazioneMax()));
    	}
        // Filtro per Costo
        if (filtro.getPrezzoMin() != null && filtro.getPrezzoMax() != null) {
        	predicates.add(cb.between(treno.get("prezzoVendita"), filtro.getPrezzoMin(), filtro.getPrezzoMax()));
        } else if (filtro.getPrezzoMin() != null) {
        	predicates.add(cb.ge(treno.get("prezzoVendita"), filtro.getPrezzoMax()));
        } else if (filtro.getPrezzoMax() != null) {
        	predicates.add(cb.ge(treno.get("prezzoVendita"), filtro.getPesoMin()));
        }
        cq.select(treno).distinct(true).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Treno> query = em.createQuery(cq);
        return query.getResultList();
    }

}
