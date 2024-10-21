package treno;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.Dao;
import com.treno.application.model.Transazione;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.utility.TrenoUtility;

@Service
public class TransazioneService {

    @Autowired
    @Qualifier("TransazioneDao")
    private Dao<Transazione> transazioneDao;
    
    @Autowired
    @Qualifier("TrenoDao")
    private TrenoUtility trenoDao;

    @Autowired
    @Qualifier("UserDao")
    private Dao<User> userDao;

    @Autowired
    @Qualifier("Transazione")
    Transazione nuovaTransazione;
    
    
    
    
   

    // Metodo per trovare una transazione per ID (ereditato da ProxyDao)
    public Transazione findById(long id) {
        return transazioneDao.findById(id);
    }

    // Metodo per trovare tutte le transazioni (ereditato da ProxyDao)
    public List<Transazione> findAll() {
        return ((Dao<Transazione>)transazioneDao).findAll();
    }
    
    
    
    
    

    
    
    
    @Transactional
    public void compraTreno(long Iduser, long idtreno) throws Exception {
    	
    	Treno treno = trenoDao.findById(idtreno);
        // Trova il venditore dal treno
        User venditore = treno.getOwner();
        User acquirente = userDao.findById(Iduser);
        // Recupera il prezzo del treno in vendita
        Double prezzo = treno.getPrezzoVendita();
        
        if (venditore == null || acquirente == null) {
            throw new Exception("Venditore o acquirente non trovato.");
        }

        // Verifica che l'acquirente abbia abbastanza fondi
        if (acquirente.getPortafoglio() < prezzo) {
            throw new IllegalArgumentException("Fondi insufficienti per completare l'acquisto!");
        }
        
        Hibernate.initialize(venditore.getPortafoglio());
        Hibernate.initialize(acquirente.getPortafoglio());

        
      
        // Esegui la transazione economica
        acquirente.setPortafoglio(acquirente.getPortafoglio() - prezzo);
        venditore.setPortafoglio(venditore.getPortafoglio() + prezzo);
        
        
        
       

        // Trasferisci il treno all'acquirente e aggiornalo
        treno.setOwner(acquirente);
        treno.setInVendita(false);
        treno.setPrezzoVendita(0);
        
        
        
        //Transazione nuovaTransazione = new Transazione();
        nuovaTransazione.setAcquirente(acquirente);
        nuovaTransazione.setVenditore(venditore);
        nuovaTransazione.setTreno(treno);
        nuovaTransazione.setImporto(prezzo);
        nuovaTransazione.setData(LocalDateTime.now());

        // Aggiorna i dati nel database
        transazioneDao.save(nuovaTransazione); // Salva la transazione
        trenoDao.update(treno);                // Aggiorna il treno con il nuovo proprietario
        userDao.update(acquirente);            // Aggiorna i dati dell'acquirente
        userDao.update(venditore);
        

       // this.registrazioneTransazione(acquirente, venditore, treno, prezzo);




    }
    
    
    
    
    
    
    
    
    
    @Transactional
    public String mettiInVendita(long idUtente, long idTreno, Double prezzoVendita) {
        // Recupera l'utente proprietario dal database tramite il suo ID
        User proprietario = userDao.findById(idUtente);
        
        // Recupera il treno dal database tramite il suo ID
        Treno treno = trenoDao.findById(idTreno);

        // Inizializza la collezione lazy vagoni
        Hibernate.initialize(treno.getVagoni());

        // Verifica che l'utente sia il proprietario del treno
        if (!treno.getOwner().equals(proprietario)) {
            return "L'utente non è il proprietario di questo treno!";
        }
        
        if (treno.isInVendita()){
        	return "dai è gia in vendita";
        	
        }

        // Verifica che il prezzo sia valido
        if (prezzoVendita == null || prezzoVendita <= 0) {
            return "Il prezzo di vendita deve essere maggiore di zero!";
        }

        // Imposta il treno come in vendita e assegna il prezzo
        treno.setInVendita(true);
        treno.setPrezzoVendita(prezzoVendita+treno.getCosto());

        // Aggiorna il treno nel database
        trenoDao.update(treno);

        return "Treno messo in vendita con successo!";
    }
       
    
    
//    
//      @Transactional
//      public void registrazioneTransazione(User acquirente, User venditore, Treno treno, Double importo) {
//          // Crea un nuovo oggetto Transazione
//          Transazione nuovaTransazione = new Transazione();
//          nuovaTransazione.setAcquirente(acquirente);
//          nuovaTransazione.setVenditore(venditore);
//          nuovaTransazione.setTreno(treno);
//          nuovaTransazione.setImporto(importo);
//          nuovaTransazione.setData(LocalDateTime.now()); // Imposta la data corrente
//
//          // Da fare poi i controlli
//          userDao.update(acquirente);
//          userDao.update(venditore);
//          trenoDao.update(treno);
//          ((Dao<Transazione>)transazioneDao).save(nuovaTransazione);
//         
//          
//
//      }
//    
//    
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}

	
	
	

	
	
	
	
	
	
	
	
	
