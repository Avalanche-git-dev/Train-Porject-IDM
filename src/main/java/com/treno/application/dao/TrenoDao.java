package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.Vagone;
import com.treno.application.utility.TrenoUtility;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class TrenoDao extends ProxyDao<Treno> implements TrenoUtility {

    public TrenoDao() {
        super(Treno.class);
    }

    /////////////////// FIND
    
    public Treno findById(long id) {
        try {
            String hql = "FROM Treno t LEFT JOIN FETCH t.valutazioni LEFT JOIN FETCH t.vagoni LEFT JOIN FETCH t.transazioni WHERE t.idTreno = :id";
            return super.em.createQuery(hql, Treno.class)
                                .setParameter("id", id)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;  
        }
    }
    
    @Transactional
    @Override
    public TrenoDTO findByTrenoId(long id) {
        String hql = "FROM Treno t LEFT JOIN FETCH t.valutazioni LEFT JOIN FETCH t.vagoni LEFT JOIN FETCH t.transazioni WHERE t.idTreno = :id";
        // Recupera l'oggetto Treno
//        Treno treno = em.createQuery(hql, Treno.class)
//                        .setParameter("id", id)
//                        .getSingleResult();
        // Stampa l'oggetto Treno per verificare i dati
       // System.out.println("Oggetto Treno: " + treno);
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
        
        return super.em.createQuery(hql, Treno.class).getResultList();
    }
    
    @Transactional
    public List<Treno> findByOwnerIdAndInVenditaFalse(Long ownerId) {
        String hql = "SELECT DISTINCT t FROM Treno t " +
                     "LEFT JOIN FETCH t.valutazioni " +
                     "LEFT JOIN FETCH t.transazioni " +
                     "WHERE t.owner.id = :ownerId " +
                     "AND t.inVendita = false"; 
        
        
        return super.em.createQuery(hql, Treno.class)
                       .setParameter("ownerId", ownerId)
                       .getResultList();
    }


   // JOIN FETCH + DISTINCT servono a risolvere il problema delle collezioni Lazy tra entità mappate.
   // In questo modo quando viene eseguita la query, si attivano le collezioni lazy e con il distinct si risolvono i doppioni.
   // A garantire comunque il tutto sono statati utilizzati Hash Set nel service, che preveiene i duplicati a sua volta.
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

    
    
    
//    @SuppressWarnings("unchecked")
//    @Transactional
//    public List<Treno> filtraTreni(TrenoFilter filtro) {
//        // Creazione della query HQL con JOIN FETCH per valutazioni e transazioni
//        StringBuilder hql = new StringBuilder("SELECT DISTINCT t FROM Treno t ");
//        hql.append("LEFT JOIN FETCH t.valutazioni v ");
//        hql.append("LEFT JOIN FETCH t.transazioni tr ");
//        hql.append("WHERE 1=1");
//
//        // Aggiunta dinamica delle condizioni di filtro
//        if (filtro.getPrezzoMin() != null) {
//            hql.append(" AND t.prezzoVendita >= :prezzoMin");
//        }
//        if (filtro.getPrezzoMax() != null) {
//            hql.append(" AND t.prezzoVendita <= :prezzoMax");
//        }
//        if (filtro.getPesoMin() != null) {
//            hql.append(" AND t.peso >= :pesoMin");
//        }
//        if (filtro.getPesoMax() != null) {
//            hql.append(" AND t.peso <= :pesoMax");
//        }
//        if (filtro.getLunghezzaMin() != null) {
//            hql.append(" AND t.lunghezza >= :lunghezzaMin");
//        }
//        if (filtro.getLunghezzaMax() != null) {
//            hql.append(" AND t.lunghezza <= :lunghezzaMax");
//        }
//        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
//            hql.append(" AND t.sigla = :sigla");
//        }
//        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
//            hql.append(" AND t.marca = :marca");
//        }
//        if (filtro.getValutazioni() != null && filtro.getValutazioni() > 0) {
//            hql.append(" AND t.valutazioneTotale >= :valutazioni");
//        }
//        if (filtro.getPrezzoVendita() != null) {
//            hql.append(" AND t.prezzoVendita = :prezzoVendita");
//        }
//        if (filtro.getAmmontareTotale() != null) {
//            hql.append(" AND t.ammontareTotale = :ammontareTotale");
//        }
//        if (Boolean.TRUE.equals(filtro.isInVendita())) {
//            hql.append(" AND t.inVendita = true");
//        } else if (Boolean.FALSE.equals(filtro.isInVendita())) {
//            hql.append(" AND t.inVendita = false");
//        }
//
//        // Creazione della query
//        Query query = em.createQuery(hql.toString());
//
//        // Impostazione dei parametri
//        if (filtro.getPrezzoMin() != null) {
//            query.setParameter("prezzoMin", filtro.getPrezzoMin());
//        }
//        if (filtro.getPrezzoMax() != null) {
//            query.setParameter("prezzoMax", filtro.getPrezzoMax());
//        }
//        if (filtro.getPesoMin() != null) {
//            query.setParameter("pesoMin", filtro.getPesoMin());
//        }
//        if (filtro.getPesoMax() != null) {
//            query.setParameter("pesoMax", filtro.getPesoMax());
//        }
//        if (filtro.getLunghezzaMin() != null) {
//            query.setParameter("lunghezzaMin", filtro.getLunghezzaMin());
//        }
//        if (filtro.getLunghezzaMax() != null) {
//            query.setParameter("lunghezzaMax", filtro.getLunghezzaMax());
//        }
//        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
//            query.setParameter("sigla", filtro.getSigla());
//        }
//        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
//            query.setParameter("marca", filtro.getMarca());
//        }
//        if (filtro.getValutazioni() != null && filtro.getValutazioni() > 0) {
//            query.setParameter("valutazioni", filtro.getValutazioni());
//        }
//        if (filtro.getPrezzoVendita() != null) {
//            query.setParameter("prezzoVendita", filtro.getPrezzoVendita());
//        }
//        if (filtro.getAmmontareTotale() != null) {
//            query.setParameter("ammontareTotale", filtro.getAmmontareTotale());
//        }
//
//        return query.getResultList();
//    }
    
    
    
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
        if (filtro.getPrezzoVendita() != null) {
            hql.append(" AND t.prezzoVendita = :prezzoVendita");
        }
        if (filtro.getPrezzoVenditaMax() != null) {
            hql.append(" AND t.prezzoVendita <= :prezzoVenditaMax");
        }

        if (Boolean.TRUE.equals(filtro.isInVendita())) {
            hql.append(" AND t.inVendita = true");
        } else if (Boolean.FALSE.equals(filtro.isInVendita())) {
            hql.append(" AND t.inVendita = false");
        }
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            hql.append(" AND t.nome = :nome");
        }
        if (filtro.getNomeOwner() != null && !filtro.getNomeOwner().isEmpty()) {
            hql.append(" AND t.owner.username = :nomeOwner");
        }

        // Aggiunta della media delle valutazioni
        if (filtro.getValutazioni() != null && filtro.getValutazioni() > 0) {
            hql.append(" GROUP BY t ");
            hql.append(" HAVING AVG(v.punteggio) >= :valutazioni");
        }

        // Aggiunta della somma delle transazioni per ammontare totale
        if (filtro.getAmmontareTotale() != null) {
            if (!hql.toString().contains("GROUP BY")) {
                hql.append(" GROUP BY t ");
            }
            hql.append(" HAVING SUM(tr.importo) >= :ammontareTotale");
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
        if (filtro.getPrezzoVenditaMax() != null) {
            query.setParameter("prezzoVenditaMax", filtro.getPrezzoVenditaMax());
        }
        if (filtro.getAmmontareTotale() != null) {
            query.setParameter("ammontareTotale", filtro.getAmmontareTotale());
        }
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", filtro.getNome());
        }
        if (filtro.getNomeOwner() != null && !filtro.getNomeOwner().isEmpty()) {
            query.setParameter("nomeOwner", filtro.getNomeOwner());
        }

       // System.out.println("HQL Query: " + hql.toString()); // Stampa la query per il debug

        return query.getResultList();
    }


    
    //Find ALL 
    @Override
    @Transactional
    public List<Treno> findAll() {
        String hql = "SELECT DISTINCT t FROM Treno t " +
                     "LEFT JOIN FETCH t.valutazioni " +
                     "LEFT JOIN FETCH t.transazioni";
        
        return super.em.createQuery(hql, Treno.class).getResultList();
    }
    
    
	
	//getVagoniByTreno
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Vagone> findVagonibyTreno(Long trenoId) {
	    String hql = "SELECT v FROM Vagone v WHERE v.treno.id = :trenoId";
	    Query query = em.createQuery(hql);
	    query.setParameter("trenoId", trenoId);
	    return (List<Vagone>)query.getResultList();
	}


}
