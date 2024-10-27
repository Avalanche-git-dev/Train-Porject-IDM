package com.treno.application.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.Vagone;
import com.treno.application.utility.TrenoUtility;

import jakarta.persistence.Query;

public class TrenoDao extends ProxyDao<Treno> implements TrenoUtility {

    public TrenoDao() {
        super(Treno.class);
    }

    /////////////////// FIND
    
    //Transactional per il fetch
    @Transactional
    @Override
    public Treno findByTrenoId(long id) {
        String hql = "FROM Treno t LEFT JOIN FETCH t.valutazioni LEFT JOIN FETCH t.vagoni LEFT JOIN FETCH t.transazioni WHERE t.idTreno = :id";
        // Recupera l'oggetto Treno
//        Treno treno = em.createQuery(hql, Treno.class)
//                        .setParameter("id", id)
//                        .getSingleResult();
        // Stampa l'oggetto Treno per verificare i dati
       // System.out.println("Oggetto Treno: " + treno);
        return em.createQuery(hql, Treno.class)
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
//        if (filtro.getCostoTotaleMin() != null) {
//            hql.append(" AND t.prezzoVendita >= :costoTotaleMin");
//        }
//        if (filtro.getCostoTotaleMax() != null) {
//            hql.append(" AND t.prezzoVendita <= :costoTotaleMax");
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
//            hql.append(" AND t.sigla LIKE :sigla"); // Usa LIKE per corrispondenza parziale
//        }
//        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
//            hql.append(" AND t.marca = :marca");
//        }
//        if (Boolean.TRUE.equals(filtro.getMediaValutazioniMin() != null)) {
//            hql.append(" AND AVG(v.punteggio) >= :mediaValutazioniMin");
//        }
//        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
//            hql.append(" AND t.nome LIKE :nome"); // Usa LIKE per corrispondenza parziale
//        }
//        if (filtro.getUsernameProprietario() != null && !filtro.getUsernameProprietario().isEmpty()) {
//            hql.append(" AND t.owner.username = :usernameProprietario");
//        }
//
//        // Creazione della query
//        Query query = em.createQuery(hql.toString());
//
//        // Impostazione dei parametri
//        if (filtro.getCostoTotaleMin() != null) {
//            query.setParameter("costoTotaleMin", filtro.getCostoTotaleMin());
//        }
//        if (filtro.getCostoTotaleMax() != null) {
//            query.setParameter("costoTotaleMax", filtro.getCostoTotaleMax());
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
//            query.setParameter("sigla", "%" + filtro.getSigla() + "%"); // Corrispondenza parziale
//        }
//        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
//            query.setParameter("marca", filtro.getMarca());
//        }
//        if (filtro.getMediaValutazioniMin() != null && filtro.getMediaValutazioniMin() > 0) {
//            query.setParameter("mediaValutazioniMin", filtro.getMediaValutazioniMin());
//        }
//        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
//            query.setParameter("nome", "%" + filtro.getNome() + "%"); // Corrispondenza parziale
//        }
//        if (filtro.getUsernameProprietario() != null && !filtro.getUsernameProprietario().isEmpty()) {
//            query.setParameter("usernameProprietario", filtro.getUsernameProprietario());
//        }
//
//        return query.getResultList();
//    }

    
//    
//    @SuppressWarnings("unchecked")
//    @Transactional
//    public List<Treno> filtraTreni(TrenoFilter filtro) {
//        StringBuilder hql = new StringBuilder("SELECT DISTINCT t FROM Treno t ");
//        hql.append("LEFT JOIN FETCH t.valutazioni v ");
//        hql.append("LEFT JOIN FETCH t.transazioni tr ");
//        hql.append("WHERE 1=1");
//
//        // Condizioni di filtro dinamiche
//        if (filtro.getCostoTotaleMin() != null) {
//            hql.append(" AND t.prezzoVendita >= :costoTotaleMin");
//        }
//        if (filtro.getCostoTotaleMax() != null) {
//            hql.append(" AND t.prezzoVendita <= :costoTotaleMax");
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
//            hql.append(" AND t.sigla LIKE :sigla");
//        }
//        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
//            hql.append(" AND t.marca = :marca");
//        }
//        if (filtro.getMediaValutazioniMin() != null) {
//            hql.append(" AND AVG(v.punteggio) >= :mediaValutazioniMin");
//        }
//        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
//            hql.append(" AND t.nome LIKE :nome");
//        }
//        if (filtro.getUsernameProprietario() != null && !filtro.getUsernameProprietario().isEmpty()) {
//            hql.append(" AND t.owner.username = :usernameProprietario");
//        }
//
//        // Aggiunta dell'ordinamento dinamico
//        if (filtro.getOrdine() != null) {
//            hql.append(" ORDER BY ");
//            switch (filtro.getOrdine()) {
//                case "sigla":
//                    hql.append("t.sigla");
//                    break;
//                case "prezzo":
//                    hql.append("t.prezzoVendita");
//                    break;
//                case "lunghezza":
//                    hql.append("t.lunghezza");
//                    break;
//                case "peso":
//                    hql.append("t.peso");
//                    break;
//                default:
//                    hql.append("t.idTreno"); // Ordinamento di default
//                    break;
//            }
//            // Aggiunta della direzione di ordinamento (ASC o DESC)
//            hql.append(" ").append(filtro.getDirezione() != null ? filtro.getDirezione() : "ASC");
//        }
//
//        Query query = em.createQuery(hql.toString());
//
//        // Impostazione dei parametri
//        if (filtro.getCostoTotaleMin() != null) {
//            query.setParameter("costoTotaleMin", filtro.getCostoTotaleMin());
//        }
//        if (filtro.getCostoTotaleMax() != null) {
//            query.setParameter("costoTotaleMax", filtro.getCostoTotaleMax());
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
//            query.setParameter("sigla", "%" + filtro.getSigla() + "%");
//        }
//        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
//            query.setParameter("marca", filtro.getMarca());
//        }
//        if (filtro.getMediaValutazioniMin() != null) {
//            query.setParameter("mediaValutazioniMin", filtro.getMediaValutazioniMin());
//        }
//        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
//            query.setParameter("nome", "%" + filtro.getNome() + "%");
//        }
//        if (filtro.getUsernameProprietario() != null && !filtro.getUsernameProprietario().isEmpty()) {
//            query.setParameter("usernameProprietario", filtro.getUsernameProprietario());
//        }
//
//        return query.getResultList();
//    }
////
    
    
    
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Treno> filtraTreni(TrenoFilter filtro) {
        StringBuilder hql = new StringBuilder("SELECT DISTINCT t FROM Treno t ");
        hql.append("LEFT JOIN FETCH t.valutazioni v ");
        hql.append("LEFT JOIN FETCH t.transazioni tr ");
        hql.append("WHERE 1=1");

        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
            hql.append(" AND t.sigla LIKE :sigla");
        }
        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
            hql.append(" AND t.marca = :marca");
        }
        if (filtro.getMediaValutazioniMin() != null) {
            hql.append(" AND AVG(v.punteggio) >= :mediaValutazioniMin");
        }
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            hql.append(" AND t.nome LIKE :nome");
        }
        if (filtro.getUsernameProprietario() != null && !filtro.getUsernameProprietario().isEmpty()) {
            hql.append(" AND t.owner.username = :usernameProprietario");
        }

        Query query = em.createQuery(hql.toString());

        if (filtro.getSigla() != null && !filtro.getSigla().isEmpty()) {
            query.setParameter("sigla", "%" + filtro.getSigla() + "%");
        }
        if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
            query.setParameter("marca", filtro.getMarca());
        }
        if (filtro.getMediaValutazioniMin() != null) {
            query.setParameter("mediaValutazioniMin", filtro.getMediaValutazioniMin());
        }
        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            query.setParameter("nome", "%" + filtro.getNome() + "%");
        }
        if (filtro.getUsernameProprietario() != null && !filtro.getUsernameProprietario().isEmpty()) {
            query.setParameter("usernameProprietario", filtro.getUsernameProprietario());
        }

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
    @Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Vagone> findVagonibyTreno(Long trenoId) {
	    String hql = "SELECT v FROM Vagone v WHERE v.treno.id = :trenoId";
	    Query query = em.createQuery(hql);
	    query.setParameter("trenoId", trenoId);
	    return (List<Vagone>)query.getResultList();
	}





}
