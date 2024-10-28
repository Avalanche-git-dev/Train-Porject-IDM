package com.treno.application.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.treno.application.Factory;
import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.exception.MotriceInMezzoException;
import com.treno.application.exception.MotricenonInTestaException;
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
		String immagine = builder2.getFactory().creaImmagine(trenoDto.getSigla(),trenoDto.getMarca());
		Treno treno = builder.creaTrenoDaStringa(trenoDto.getSigla());
		User owner = new User();
		owner.setUserId(utenteDto.getUserId());
		treno.setOwner(owner);
		treno.setMarca(trenoDto.getMarca());
		treno.setNome(trenoDto.getNome());
		treno.setValore(treno.getCosto());
		treno.setImmagine(immagine);
		
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
		Treno treno = trenoDao.findById(Long.valueOf(idTreno)); // Trova il treno tramite l'ID
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
	        trenoDTO.setValore(treno.getValore());

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
		    treno.setValore(trenoDTO.getValore());
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
	 
	 
	 
	   //Inverti vagoni e sigla del treno
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

	    // Metodo per copiare un treno esistente, dandogli il nome distintivo copia -05
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
        // aggiungi un Vagone 
	    @Transactional
	    public TrenoDTO aggiungiVagone(Long idTreno, String tipoVagone) throws TrenoCreazioneException {
	        // Trova il treno dal database
	        Treno treno = trenoDao.findById(idTreno);
	        
	        if (treno == null) {
	            throw new TrenoCreazioneException("Treno non trovato.");
	        }
	        TBuilder builder2 = (TBuilder) builder;
	        Factory factory =  builder2.getFactory();
	        if((treno.getMarca()==null)||((treno.getMarca()).equals("")))
	        {
	        	throw new TrenoCreazioneException("Contatta l'amminitratore il tuo treno risulta essere di una marca sonosciuta.");
	        }
	        factory.setMarca(treno.getMarca());
	        
	        String sigla=treno.getSigla();
	        
	        if (!sigla.isEmpty() && !sigla.startsWith("H")) {
	            throw new MotricenonInTestaException("La motrice deve essere in testa alla sigla.");
	        }
	        
	        // Controllo: Non ci devono essere altre motrici oltre la prima
	        boolean motriceTrovata = false;
	        for (int i = 1; i < sigla.length(); i++) { // Parte dall'indice 1 per saltare la prima posizione
	            if (sigla.charAt(i) == 'H') {
	                motriceTrovata = true;
	                break;
	            }
	            if (motriceTrovata) {
		            throw new MotriceInMezzoException("Non possono esserci altre motrici in mezzo alla sigla.");
		        }
		        
	            
	        }
	       
	        if (sigla.endsWith("H")) {
	            // Controllo: nessun vagone può essere aggiunto dopo una motrice
	            throw new TrenoCreazioneException("Non è possibile aggiungere vagoni dopo una motrice.");
	        }

	        
	        
	        if (tipoVagone.equals("R")) {
	            long countR = sigla.chars().filter(ch -> ch == 'R').count();
	            if (countR >= 2) {
	                throw new TrenoCreazioneException("Troppi ristoranti.");
	            }
	        }

	        

	        // Crea il vagone basato sul tipo
	        Vagone vagone;
	        switch (tipoVagone) {
	            case "P":
	                vagone = factory.creaPasseggeri() ; // Supponendo che Passeggero sia una classe che estende Vagone
	                break;
	            case "R":
	                vagone = factory.creaRistorante(); // Supponendo che Ristorante sia una classe che estende Vagone
	                break;
	            case "C":
	                vagone = factory.creaCargo(); // Supponendo che Cargo sia una classe che estende Vagone
	                break;
	            case "H":
	                vagone = factory.creaMotrice(); // Supponendo che Motrice sia una classe che estende Vagone
	                break;
	            default:
	                throw new TrenoCreazioneException("Tipo di vagone non valido: " + tipoVagone);
	        }
            treno.setSigla(sigla.concat(tipoVagone));
	        treno.add(vagone); // Assumendo che Treno abbia un metodo aggiungiVagone

	        // Salva il treno aggiornato nel database
	        trenoDao.update(treno);
	        
	        return convertToTrenoDTO(treno);
	    }


	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

	    
	    
	    @Transactional
	    public TrenoDTO rimuoviVagone(Long trenoId, long vagoneId) {
	        Treno treno = trenoDao.findById(trenoId);
	        if (treno == null) {
	            throw new TrenoCreazioneException("Treno non trovato con ID: " + trenoId);
	        }

	        List<Vagone> vagoni = treno.getVagoni();
	        
	        // Trova l'indice del vagone da rimuovere
	        int indiceVagone = -1;
	        for (int i = 0; i < vagoni.size(); i++) {
	            if ((vagoni.get(i).getIdVagone())==vagoneId) {
	                indiceVagone = i;
	                break;
	            }
	        }
	        
	        if (indiceVagone == -1) {
	            throw new TrenoCreazioneException("Vagone non trovato con ID: " + vagoneId);
	        }

	        // Rimuovi il vagone dalla lista
	        vagoni.remove(indiceVagone);
	        treno.setVagoni(vagoni);

	        // Aggiorna la sigla rimuovendo il carattere corrispondente
	        String sigla = treno.getSigla();
	        if (indiceVagone >= 0 && indiceVagone < sigla.length()) {
	            sigla = sigla.substring(0, indiceVagone) + sigla.substring(indiceVagone + 1);
	        }
	        
	        
	        
	        if (sigla.isEmpty()) {
	            trenoDao.delete(treno); // Elimina il treno dal database
	            throw new TrenoCreazioneException("L'ultimo vagone è stato rimosso. Treno cancellato.");
	        }
	        
	        
	        treno.setSigla(sigla);

	        // Salva il treno aggiornato nel database
	        trenoDao.update(treno);
	        
	        return convertToTrenoDTO(treno);
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public List<Vagone> findVagoniByTreno(Long idTreno) {
	        return trenoDao.findVagonibyTreno(idTreno);
	    }
	    
	    
	    
	    public List<Treno> filtraTreniConServizio(TrenoFilter filtro) {
	        List<Treno> treniFiltrati = trenoDao.filtraTreni(filtro);

	        // Filtraggio per lunghezza, peso e costo
	        return treniFiltrati.stream()
	            .filter(treno -> filtro.getLunghezzaMin() == null || treno.getLunghezza() >= filtro.getLunghezzaMin())
	            .filter(treno -> filtro.getLunghezzaMax() == null || treno.getLunghezza() <= filtro.getLunghezzaMax())
	            .filter(treno -> filtro.getPesoMin() == null || treno.getPeso() >= filtro.getPesoMin())
	            .filter(treno -> filtro.getPesoMax() == null || treno.getPeso() <= filtro.getPesoMax())
	            .filter(treno -> filtro.getCostoTotaleMin() == null || treno.getPrezzoVendita() >= filtro.getCostoTotaleMin())
	            .filter(treno -> filtro.getCostoTotaleMax() == null || treno.getPrezzoVendita() <= filtro.getCostoTotaleMax())
	            .sorted((t1, t2) -> {
	                if (filtro.getOrdine() == null) return 0;
	                int result;
	                switch (filtro.getOrdine()) {
	                    case "sigla":
	                        result = t1.getSigla().compareTo(t2.getSigla());
	                        break;
	                    case "prezzo":
	                        result = Double.compare(t1.getPrezzoVendita(), t2.getPrezzoVendita());
	                        break;
	                    case "lunghezza":
	                        result = Double.compare(t1.getLunghezza(), t2.getLunghezza());
	                        break;
	                    case "peso":
	                        result = Double.compare(t1.getPeso(), t2.getPeso());
	                        break;
	                    default:
	                        result = t1.getIdTreno().compareTo(t2.getIdTreno());
	                        break;
	                }
	                return filtro.getDirezione() != null && filtro.getDirezione().equalsIgnoreCase("DESC") ? -result : result;
	            })
	            .collect(Collectors.toList());
	    }
	    
	    
//	    public List<Treno> filtraTreniConServizio(TrenoFilter filtro) {
//	        List<Treno> treniFiltrati = trenoDao.filtraTreni(filtro);
//
//	        // Filtraggio per lunghezza, peso e costo
//	        return treniFiltrati.stream()
//	            .filter(treno -> filtro.getLunghezzaMin() == null || treno.getLunghezza() >= filtro.getLunghezzaMin())
//	            .filter(treno -> filtro.getLunghezzaMax() == null || treno.getLunghezza() <= filtro.getLunghezzaMax())
//	            .filter(treno -> filtro.getPesoMin() == null || treno.getPeso() >= filtro.getPesoMin())
//	            .filter(treno -> filtro.getPesoMax() == null || treno.getPeso() <= filtro.getPesoMax())
//	            .filter(treno -> filtro.getCostoTotaleMin() == null || treno.getPrezzoVendita() >= filtro.getCostoTotaleMin())
//	            .filter(treno -> filtro.getCostoTotaleMax() == null || treno.getPrezzoVendita() <= filtro.getCostoTotaleMax())
//	            .collect(Collectors.toList());
//	    }
	    
	    
	    
//




}
