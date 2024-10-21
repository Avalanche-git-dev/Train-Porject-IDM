package com.treno.application.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;
import com.treno.application.utility.TrenoUtility;
import com.treno.application.utility.UtenteValutaTreno;

public class TrenoService {

	@Autowired
	@Qualifier("Builder")
	private TrenoBuilder builder;

	@Autowired
	@Qualifier("TrenoDao")
	private TrenoUtility trenoDao;
	
	
	@Autowired
	@Qualifier ("UserService")
	private UserService userService;
	
	@Autowired
	@Qualifier ("ValutazioneDao")
	private UtenteValutaTreno valutazioneDao;

	
	
	//Creazione del treno
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
		// Salva il treno tramite DAO
		trenoDao.save(treno);
	}
	

	// classico by uiser
	@Transactional
	public List<TrenoDTO> findAllTreniByUser(long userId) {
	    // Recupera tutti i treni di un utente specifico dal DAO
	    List<Treno> treni = trenoDao.findAllTreniByUser(userId);

	    // Converte ogni Treno in TrenoDTO usando il metodo convertToDTO
	    return treni.stream()
	                .map(this::convertToTrenoDTO) // Conversione di ogni Treno in TrenoDTO
	                .collect(Collectors.toList()); // Restituisce la lista di TrenoDTO
	}
	
	
	//Get ALL
	public List<TrenoDTO> getAllTreni() {
	    // Recupera tutti i treni dal DAO
	    List<Treno> treni = trenoDao.findAll();

	    // Converte ogni Treno in TrenoDTO usando il metodo convertToDTO
	    return treni.stream()
	                .map(this::convertToTrenoDTO) // Conversione di ogni Treno in TrenoDTO
	                .collect(Collectors.toList()); // Restituisce la lista di TrenoDTO
	}

	// Aggiorna un treno
	@Transactional
	public void update(TrenoDTO trenoDTO) {
	    Treno treno = convertToEntity(trenoDTO); // Converte il DTO in entità
	    trenoDao.update(treno);
	}
	// cancella un treno no DTO
	@Transactional
	public void cancellaTreno(Long idTreno) {
		Treno treno = trenoDao.findById(idTreno); // Trova il treno tramite l'ID
		if (treno != null) {
			trenoDao.delete(treno); 
		} else {
			throw new IllegalArgumentException("Treno non trovato con l'ID: " + idTreno);
		}
	}
    //Filtra treni da passare al Dao
//	public List<TrenoDTO> filtraTreni(TrenoFilter filtro) {
//	    List<Treno> treniFiltrati = ((TrenoUtility) trenoDao).filtraTreni(filtro);
//	    return treniFiltrati.stream().map(this::convertToTrenoDTO).collect(Collectors.toList());
//	}
	
	// Proviamo con hasset
	public Set<TrenoDTO> filtraTreni(TrenoFilter filtro) {
	    List<Treno> treniFiltrati = ((TrenoUtility) trenoDao).filtraTreni(filtro);

	    // Convertire la lista in un set per evitare duplicati
	    return treniFiltrati.stream()
	                        .map(this::convertToTrenoDTO)
	                        .collect(Collectors.toSet());
	}

	
    //Classico da override di Interfacia
	public TrenoDTO findById(Long id) {
	    Treno treno = trenoDao.findById(id);
	    return convertToTrenoDTO(treno);
	}
	
	
	
	//Metodi di servizio per non fare query impossibili........ non si puo gestire questo tipo di calcolo a livello db .
	/*<------------------------------------>*/
	
	
	
	
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

	
	//Lista converitta
	public List<TrenoDTO> convertToDTOList(List<Treno> treni) {
        return treni.stream()
                    .map(this::convertToTrenoDTO)
                    .collect(Collectors.toList());
    }
	

	 
//	 public void valutaTreno(ValutazioneDTO valutazioneDto) {
//	        Valutazione valutazione = new Valutazione();
//	        valutazione.setIdTreno(valutazioneDto.getIdTreno());
//	        valutazione.setIdUtente(valutazioneDto.getIdUtente());
//	        valutazione.setValutazione(valutazioneDto.getValutazione());
//	        valutazioneDao.save(valutazione);
//	    }

	
	//Converti dto
	 public TrenoDTO convertToTrenoDTO(Treno treno) {
	        TrenoDTO trenoDTO = new TrenoDTO();
	        
	        // Mappa i campi dall'entità Treno al DTO
	        trenoDTO.setIdTreno(treno.getIdTreno());
	        trenoDTO.setNome(treno.getNome());
	        trenoDTO.setSigla(treno.getSigla());
	        trenoDTO.setImmagine(treno.getImmagine());
	        trenoDTO.setInVendita(treno.isInVendita());
	        trenoDTO.setPrezzoVendita(treno.getPrezzoVendita());
	        trenoDTO.setMarca(treno.getMarca());
	        trenoDTO.setMediaValutazioni(treno.getMediaValutazioni());
	        trenoDTO.setPesoTotale(treno.getPeso());
	        trenoDTO.setPostiTotali(treno.getPostiTotali());
	        trenoDTO.setCostoTotale(treno.getCosto());
	        trenoDTO.setLunghezzaTotale(treno.getLunghezza());
	        trenoDTO.setIdOwner(treno.getOwner().getUserId());

	        return trenoDTO;
	    }
	 //Contrario
	 public Treno convertToEntity(TrenoDTO trenoDTO) {
		    Treno treno = new Treno();

		    // Mappa i campi dal DTO all'entità
		    treno.setIdTreno(trenoDTO.getIdTreno());
		    treno.setNome(trenoDTO.getNome());
		    treno.setSigla(trenoDTO.getSigla());
		    treno.setImmagine(trenoDTO.getImmagine());
		    treno.setInVendita(trenoDTO.isInVendita());
		    treno.setPrezzoVendita(trenoDTO.getPrezzoVendita());
		    treno.setMarca(trenoDTO.getMarca());
		    if ((Long) ( trenoDTO.getIdOwner()) != null) {
		    //Conversione dell'utente e assegnazione.
		    UserDTO Owner = userService.findById(trenoDTO.getIdOwner());
		    treno.setOwner(userService.convertToUserEntity(Owner));
		    }
		    // Valutazioni e transazioni di solito non vengono mappate direttamente al DTO
		    // a meno che non le passi esplicitamente.

		    return treno;
		}
	 
	 public List<TrenoDTO> findTreniInVendita() {
		    List<Treno> treniInVendita = trenoDao.findAllInVendita();
		    return treniInVendita.stream().map(this::convertToTrenoDTO).collect(Collectors.toList());
		}
	 
	 public List<TrenoDTO> findTreniByUserEscludiInVendita(Long ownerId) {
		    List<Treno> treniUtente = trenoDao.findByOwnerIdAndInVenditaFalse(ownerId);
		    return treniUtente.stream().map(this::convertToTrenoDTO).collect(Collectors.toList());
	 }


}
