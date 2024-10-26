package com.treno.application.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.TrenoCreazioneException;
import com.treno.application.exception.UserNotFoundException;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Treno;
import com.treno.application.model.User;
import com.treno.application.model.Vagone;
import com.treno.application.model.builder.TBuilder;
import com.treno.application.model.builder.TrenoBuilder;
import com.treno.application.utility.TrenoUtility;

public class TrenoService {

	@Autowired
	@Qualifier("Builder")
	private TrenoBuilder builder;

	@Autowired
	@Qualifier("TrenoDao")
	private TrenoUtility trenoDao;

	@Autowired
	@Qualifier("UserService")
	private UserService userService;

//	@Autowired
//	@Qualifier("ValutazioneDao")
//	private UtenteValutaTreno valutazioneDao;
	
	
	
	
	
	

	// Creazione del treno
	@Transactional
	public TrenoDTO creaTreno(TrenoDTO trenoDto, UserDTO utenteDto) {
		TBuilder builder2 = (TBuilder) builder;
		builder2.getFactory().setMarca(trenoDto.getMarca());
		

		Treno treno = builder.creaTrenoDaStringa(trenoDto.getSigla());

		User owner = new User();
		owner.setUserId(utenteDto.getUserId());
		treno.setOwner(owner);
		treno.setMarca(trenoDto.getMarca());
		treno.setNome(trenoDto.getNome());
		
		TrenoDTO trenoR = convertToTrenoDTO(treno);
		
		trenoDao.save(treno);
		return trenoR;
	}

	// Classico by user
	@Transactional
	public List<TrenoDTO> findAllTreniByUser(long userId) {
		List<Treno> treni = trenoDao.findAllTreniByUser(userId);

		return treni.stream().map(this::convertToTrenoDTO) 
				.collect(Collectors.toList()); // restituisce la lista di TreniDTO per per idUser
	}

	// Get ALL
	public List<TrenoDTO> getAllTreni() {
		List<Treno> treni = trenoDao.findAll();
		return treni.stream().map(this::convertToTrenoDTO) 
				.collect(Collectors.toList()); // restituisce la lista di TreniDTO totale.
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
			throw new TrenoCreazioneException("Treno non trovato con l'ID: " + idTreno);
		}
	}

	

	public TrenoDTO findById(Long id) {
		Treno treno = trenoDao.findByTrenoId(Long.valueOf(id));
		return convertToTrenoDTO(treno);
	}
	
	public Treno findByid(Long id) {
		Treno treno= trenoDao.findById(Long.valueOf(id));
		return treno;
	}

	// Metodi di servizio per non fare query impossibili........ non si puo gestire
	// questo tipo di calcolo a livello db .
	/* <------------------------------------> */

	public double getValutazioneTotale(Treno treno) {
		return treno.getValutazioneTotale();
	}

	public double getMediaValutazioni(Treno treno) {
		return treno.getMediaValutazioni();
	}

	public double getPesoTotale(Treno treno) {
		return treno.getPeso();
	}

	public double getCostoTotale(Treno treno) {
		return treno.getCosto();
	}

	public double getLunghezzaTotale(Treno treno) {
		return treno.getLunghezza();
	}

	public boolean parte(Treno treno) {
		return treno.parte();
	}

	public double getPostiTotali(Treno treno) {
		return treno.getPostiTotali();
	}

	// Lista converitta
	public List<TrenoDTO> convertToDTOList(List<Treno> treni) {
		return treni.stream().map(this::convertToTrenoDTO).collect(Collectors.toList());
	}

	
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

		    treno.setIdTreno(trenoDTO.getIdTreno());
		    treno.setNome(trenoDTO.getNome());
		    treno.setSigla(trenoDTO.getSigla());
		    treno.setImmagine(trenoDTO.getImmagine());
		    treno.setInVendita(trenoDTO.isInVendita());
		    treno.setPrezzoVendita(trenoDTO.getPrezzoVendita());
		    treno.setMarca(trenoDTO.getMarca());
		    if ((Long) ( trenoDTO.getIdOwner()) != null) {
		    UserDTO Owner = userService.findById(trenoDTO.getIdOwner());
		    treno.setOwner(userService.convertToUserEntity(Owner));
		    }

	


		// Valutazioni e transazioni di solito non vengono mappate direttamente al DTO
		// a meno che non le passi esplicitamente.

		return treno;
	}
	 
	 
	 
	 // FindTreniByUsername
	@Transactional
	public List<TrenoDTO> findTreniByUsername(String username) {
		UserDTO user = userService.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("Utente non trovato con username: " + username);
		}

		List<Treno> treni = trenoDao.findAllTreniByUser(user.getUserId());

		return treni.stream().map(this::convertToTrenoDTO) // Conversione di ogni Treno in TrenoDTO
				.collect(Collectors.toList()); // Restituisce la lista di TrenoDTO
	}
	
	//FindTreniByFilter
	    @Transactional
	    public List<TrenoDTO> findTreniByFilter(TrenoFilter filter) {
	        List<Treno> treni = trenoDao.filtraTreni(filter);

	        return treni.stream()
	                    .map(this::convertToTrenoDTO) // Conversione di ogni Treno in TrenoDTO
	                    .collect(Collectors.toList()); // Restituisce la lista di TrenoDTO
	    }
//	
//	
	
	

	
	
	 // FindAllInVendita
	 public List<TrenoDTO> findTreniInVendita() {
		    List<Treno> treniInVendita = trenoDao.findAllInVendita();
		    return treniInVendita.stream().map(this::convertToTrenoDTO).collect(Collectors.toList());
		}
	 
	 
	 // FindTreniNonInVenditByUser
	 public List<TrenoDTO> findTreniByUserEscludiInVendita(Long ownerId) {
		    List<Treno> treniUtente = trenoDao.findByOwnerIdAndInVenditaFalse(ownerId);
		    return treniUtente.stream().map(this::convertToTrenoDTO).collect(Collectors.toList());
	 }
	 
	 
	 
	 
	   @Transactional
	    public TrenoDTO invertiVagoni(Long trenoId) {
	        Treno treno = trenoDao.findById(trenoId);
	        if (treno == null) {
	            throw new IllegalArgumentException("Treno non trovato con ID: " + trenoId);
	        }

	        List<Vagone> vagoni = treno.getVagoni();
	        Collections.reverse(vagoni); // Inverte l'ordine dei vagoni
	        treno.setVagoni(vagoni);
	        String sigla = treno.getSigla();
	        String siglaReverse = new StringBuilder(sigla).reverse().toString();
	        treno.setSigla(siglaReverse);
	        trenoDao.update(treno);
	        return convertToTrenoDTO(treno);
	    }

	    // Metodo per copiare un treno esistente
	    @Transactional
	    public TrenoDTO copiaTreno(Long trenoId) {
	        Treno trenoOriginale = trenoDao.findById(trenoId);
	        if (trenoOriginale == null) {
	            throw new TrenoCreazioneException("Treno non trovato con ID: " + trenoId);
	        }

	        Treno trenoCopia = new Treno();
	        trenoCopia.setImmagine(trenoOriginale.getImmagine());
	        trenoCopia.setSigla(trenoOriginale.getSigla());
	        trenoCopia.setNome(trenoOriginale.getNome() + "_copia");
	        trenoCopia.setMarca(trenoOriginale.getMarca());
	        trenoCopia.setOwner(trenoOriginale.getOwner());
	        trenoCopia.setVagoni(new ArrayList<>(trenoOriginale.getVagoni())); // Copia dei vagoni
	        trenoDao.save(trenoCopia);

	        return convertToTrenoDTO(trenoCopia);
	    }

	    // Metodo per aggiungere uno o più vagoni a un treno
	    @Transactional
	    public void aggiungiVagone(Long trenoId, Vagone... nuoviVagoni) {
	        Treno treno = trenoDao.findById(trenoId);
	        if (treno == null) {
	            throw new TrenoCreazioneException("Treno non trovato con ID: " + trenoId);
	        }

	        List<Vagone> vagoni = treno.getVagoni();
	        vagoni.addAll(Arrays.asList(nuoviVagoni)); // Aggiunge i nuovi vagoni
	        treno.setVagoni(vagoni);
	        trenoDao.update(treno);
	    }

	    // Metodo per rimuovere un vagone da un treno
	    @Transactional
	    public void rimuoviVagone(Long trenoId, long vagoneId) {
	        Treno treno = trenoDao.findById(trenoId);
	        if (treno == null) {
	            throw new TrenoCreazioneException("Treno non trovato con ID: " + trenoId);
	        }

	        List<Vagone> vagoni = treno.getVagoni();
	        vagoni.removeIf(vagone ->Long.valueOf((vagone.getIdVagone())).equals(vagoneId)); // Rimuove il vagone specifico
	        treno.setVagoni(vagoni);
	        trenoDao.update(treno);
	    }
	    
	    
	    
	    
	    public List<Vagone> findVagoniByTreno(Long idTreno) {
	        return trenoDao.findVagonibyTreno(idTreno);
	    }



}
