package com.treno.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dao.TrenoUtility;
import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;


@Service
public class TrenoService {

	@Autowired
	@Qualifier("Builder")
	private TrenoBuilder builder;

	@Autowired
	@Qualifier("TrenoDao")
	private TrenoUtility trenoDao;

	// Owner gestito trasmite sessione quindi arriva a livello service convalidato.
//	@Transactional
//	public void creaTreno(String input, UserDTO utenteDto, String marca) {
//		TBuilder builder2 = (TBuilder) builder;
//		builder2.getFactory().setMarca(marca);
//		Treno treno = builder.creaTrenoDaStringa(input);
//		treno.setOwner(utenteDto);
//		trenoDao.save(treno);
//	}
	
	
	@Transactional
	public void creaTreno(TrenoDTO trenoDto, UserDTO utenteDto) {
	    // Utilizza il builder per creare un treno basato sui dati del DTO
	    TBuilder builder2 = (TBuilder) builder;
	    builder2.getFactory().setMarca(trenoDto.getMarca());
	    
	    // Costruisci il treno e imposta i parametri dal DTO
	    Treno treno = builder.creaTrenoDaStringa(trenoDto.getSigla());
	    
	    User owner = new User();
	    owner.setUserId(utenteDto.getUserId());
	    treno.setOwner(owner);
	    treno.setMarca(trenoDto.getMarca());
	    treno.setNome(trenoDto.getNome());
	    // Crea un oggetto User solo con ID e username per evitare complessità
	    //treno.setInVendita(trenoDto.isInVendita());
	   // treno.setImmagine(trenoDto.getImmagine());
	    //treno.setPrezzoVendita(trenoDto.getPrezzoVendita());
	    
	    // Salva il treno tramite DAO
	    trenoDao.save(treno);
	}

	public Treno findByT(Treno treno) {
		return trenoDao.findById(treno.getIdTreno());
	}
	@Transactional
	public List<Treno> findAllTreniByUser(long userId) {
        return trenoDao.findAllTreniByUser(userId);
    }
	
	@Transactional
	public void update(Treno treno) {
		trenoDao.update(treno);

	}
	
	
	//Metodo pronto per i filtri 

	public List<Treno> filtraTreni(TrenoFilter filtro, long userId) {
	    return ((TrenoUtility) trenoDao).filtraTreni(filtro, userId);
	}

	public Treno findById(Long id) {
		return trenoDao.findById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 // Calcola il numero totale di valutazioni
    public double getValutazioneTotale(Treno treno) {
        return treno.getValutazioneTotale();
    }

    // Calcola la media delle valutazioni
    public double getMediaValutazioni(Treno treno) {
        return treno.getMediaValutazioni();
    }

    // Calcola il peso totale dei vagoni
    public double getPesoTotale(Treno treno) {
        return treno.getPeso();
    }

    // Calcola il costo totale dei vagoni
    public double getCostoTotale(Treno treno) {
        return treno.getCosto();
    }

    // Calcola la lunghezza totale dei vagoni
    public double getLunghezzaTotale(Treno treno) {
        return treno.getLunghezza();
    }

    // Determina se il treno può partire
    public boolean parte(Treno treno) {
        return treno.parte();
    }

    // Calcola il numero totale di posti passeggeri
    public double getPostiTotali(Treno treno) {
        return treno.getPostiTotali();
    }
	
	             

}
