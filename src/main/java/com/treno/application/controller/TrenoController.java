package com.treno.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.treno.application.dto.TrenoDTO;
import com.treno.application.dto.UserDTO;
import com.treno.application.dto.UserGuest;
import com.treno.application.exception.TrenoCreazioneException;
import com.treno.application.filter.TrenoFilter;
import com.treno.application.model.Cargo;
import com.treno.application.model.Motrice;
import com.treno.application.model.Passeggero;
import com.treno.application.model.Ristorante;
import com.treno.application.model.Vagone;
import com.treno.application.service.TrenoService;
import com.treno.application.utility.SessioneUtility;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/treni")
public class TrenoController {

	@Autowired
	@Qualifier("TrenoService")
	TrenoService trenoService;

	@Autowired
	@Qualifier("Sessione")
	private SessioneUtility sessione;

	@GetMapping
	public String mostraTreni(HttpSession session, Model model) {
		UserDTO utenteLoggato = sessione.getUtenteLoggato(session);

//        if(!sessione.isUtenteLoggato(session)) {
//        	return sessione.redirectTologin();
//        }
		sessione.setUtenteLoggato(session, utenteLoggato);

		model.addAttribute("utenteLoggato", utenteLoggato);
		return "treni";
	}

	// Creazione Guest
	@GetMapping("/crea/guest")
	public String mostraCreaGuest(HttpSession session, Model model) {
		// Recupera l'utente guest dalla sessione

		UserGuest guest = (UserGuest) sessione.getUtenteLoggato(session);
		if (sessione.isUtenteGuest(session)) {
			model.addAttribute(guest);
		}

		model.addAttribute("treno", new TrenoDTO());
		model.addAttribute("guest", guest);
		sessione.setUtenteLoggato(session, guest);

		// Restituisce la vista del form di creazione
		return "crea";
	}

	// Creazione Guest

	@PostMapping("/crea/guest")
	public String creaTrenoGuest(@RequestParam("nomeTreno") String nomeTreno, @RequestParam("input") String input,
			@RequestParam("marca") String marca, HttpSession session, Model model) {
		// Recupera la lista dei treni dalla sessione, o creane una nuova se non esiste
		@SuppressWarnings("unchecked")
		List<TrenoDTO> treniGuest = (List<TrenoDTO>) session.getAttribute("treniGuest");
		if (treniGuest == null) {
			treniGuest = new ArrayList<>();
		}

		// Crea un nuovo TrenoDTO e popola i campi
		TrenoDTO treno = new TrenoDTO();
		treno.setNome(nomeTreno);
		treno.setSigla(input);
		treno.setMarca(marca);

		// Aggiungi il nuovo treno alla lista
		treniGuest.add(treno);

		// Salva la lista aggiornata nella sessione
		session.setAttribute("treniGuest", treniGuest);

		// Mostra di nuovo la pagina per creare altri treni o andare alla registrazione
		model.addAttribute("treniGuest", treniGuest);

		// Recupera l'utente guest dalla sessione e aggiorna lo stato dell'utente
		// loggato
		UserGuest guest = (UserGuest) session.getAttribute("utenteGuest");
		sessione.setUtenteLoggato(session, guest);

		return "crea"; // Rimani sulla stessa pagina per creare altri treni
	}

	// <-----------------------------------------------

	// getCrea
	@GetMapping("/crea")
	public String mostraFormCreazioneTreno(HttpSession session, Model model) {
	    UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
	    model.addAttribute("utenteLoggato", utenteLoggato);
	    model.addAttribute("treno", new TrenoDTO());

	    List<TrenoDTO> listaTreni = trenoService.findAllTreniByUser(utenteLoggato.getUserId());
	    model.addAttribute("listaTreniUtente", listaTreni);
	    Map<Long, String> vagoneTypeMap = new HashMap<>();
	    for (TrenoDTO treno : listaTreni) {
	        List<Vagone> listaVagoni = trenoService.findVagoniByTreno(treno.getIdTreno());
	        
	        for (Vagone vagone : listaVagoni) {
	            if (vagone instanceof Passeggero) {
	                vagoneTypeMap.put(vagone.getIdVagone(), "Vagone Passeggeri");
	            } else if (vagone instanceof Ristorante) {
	                vagoneTypeMap.put(vagone.getIdVagone(), "Vagone Ristorante");
	            } else if (vagone instanceof Cargo) {
	                vagoneTypeMap.put(vagone.getIdVagone(), "Vagone Cargo");
	            } else if (vagone instanceof Motrice) {
	                vagoneTypeMap.put(vagone.getIdVagone(), "Vagone Motrice");
	            } else {
	                vagoneTypeMap.put(vagone.getIdVagone(), "Tipo Sconosciuto");
	            }
	        }
	        treno.setVagoni(listaVagoni);
	        
	    }
	    model.addAttribute("vagoneTypeMap", vagoneTypeMap);
	   
	    return "crea";
	}
	
	
//	
////	
//	@GetMapping("/crea")
//	public String mostraFormCreazioneTreno(HttpSession session, Model model) {
//	    UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
//	    model.addAttribute("utenteLoggato", utenteLoggato);
//	    model.addAttribute("treno", new TrenoDTO());
//
//	    List<TrenoDTO> listaTreni = trenoService.findAllTreniByUser(utenteLoggato.getUserId());
//	    for (TrenoDTO treno : listaTreni) {
//	        List<Vagone> listaVagoni = trenoService.findVagoniByTreno(treno.getIdTreno());
//	        treno.setVagoni(listaVagoni);
//	        
//	    }
//
//	    model.addAttribute("listaTreniUtente", listaTreni);
//	    return "crea";
//	}

	// DoCrea
//	@PostMapping("/crea")
//	public String creaTreno(@RequestParam("nomeTreno") String nomeTreno, @RequestParam("input") String input,
//			@RequestParam("marca") String marca, HttpSession session, Model model) {
//		UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
//		try {
//			TrenoDTO trenoCreato = new TrenoDTO();
//			trenoCreato.setNome(nomeTreno);
//			trenoCreato.setSigla(input);
//			trenoCreato.setMarca(marca);
//			TrenoDTO nuovoTreno = trenoService.creaTreno(trenoCreato, utenteLoggato);
//			model.addAttribute("nuovoTreno", nuovoTreno);
//		} catch (TrenoCreazioneException e) {
//			model.addAttribute("errorMessage", e.getMessage());
//			return "crea";
//
//		}
//
//		return "redirect:/treni/crea";
//	}
	
	
	@PostMapping("/crea")
	public String creaTreno(@RequestParam("nomeTreno") String nomeTreno, @RequestParam("input") String input,
	                        @RequestParam("marca") String marca, HttpSession session, RedirectAttributes redirectAttributes) {
	    UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
	    
	    try {
	        TrenoDTO trenoCreato = new TrenoDTO();
	        trenoCreato.setNome(nomeTreno);
	        trenoCreato.setSigla(input);
	        trenoCreato.setMarca(marca);
	        TrenoDTO nuovoTreno = trenoService.creaTreno(trenoCreato, utenteLoggato);
	        redirectAttributes.addFlashAttribute("nuovoTreno", nuovoTreno);
	        redirectAttributes.addFlashAttribute("successMessage", "Treno creato con successo: " + nuovoTreno.getNome());
	    } catch (TrenoCreazioneException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	        return "redirect:/treni/crea";
	    }

	    return "redirect:/treni/crea";
	}


	@GetMapping("/visualizza")
	public String visualizzaTreniPerUtente(Model model, HttpSession session) {
		// Verifica se l'utente è loggato e, se non lo è, reindirizza alla pagina di
		// login
		if (!sessione.isUtenteLoggato(session)) {
			return sessione.redirectTologin();
		}

		UserDTO utenteLoggato = sessione.getUtenteLoggato(session);
		Long ownerId = utenteLoggato.getUserId();
		String usernameOwner = utenteLoggato.getUsername();

		// Aggiunge un percorso di immagine predefinito per i treni
		String immagineTreno = "/ProgettoTreno/resources/images/treni/trenoTedesco.jpg";
		session.setAttribute("immagineTreno", immagineTreno);

		List<TrenoDTO> treniDto = trenoService.findAllTreniByUser(ownerId);

		model.addAttribute("treniDto", treniDto);
		model.addAttribute("ownerId", ownerId);
		session.setAttribute("usernameOwner", usernameOwner);

		return "visualizzaTreni";
	}

	@GetMapping("/filtro")
	public String filtroTreni(@ModelAttribute TrenoFilter filter, Model model, HttpSession session) {

		List<TrenoDTO> treniFiltrati = trenoService.findTreniByFilter(filter);

		model.addAttribute("treniDto", treniFiltrati);

		model.addAttribute("filter", filter);

		return "visualizzaTreni";
	}

	// O post con il dto o get con id tramite post nascosto .
	@GetMapping("/visualizza/treno")
	public String visualizzaTreno(HttpSession session, Model model) {
		// Recupera l'ID del treno dalla sessione
		Long idTreno = (Long) session.getAttribute("trenoSelezionato");

		if (idTreno == null) {
			model.addAttribute("errorMessage", "Seleziona un treno dalla collezione.");
			return "redirect:/catalogo";
		}

		// Recupera i dettagli del treno tramite il servizio
		TrenoDTO trenoSelezionato = trenoService.findById(idTreno);
		if (trenoSelezionato == null) {
			model.addAttribute("errorMessage", "Il treno non è più disponibile.");
			return "redirect:/catalogo";
		}

		// Aggiungi i dettagli del treno al modello
		model.addAttribute("treno", trenoSelezionato);
		model.addAttribute("ownerId", trenoSelezionato.getIdOwner());

		return "dettagliTreno"; // Restituisce la vista dei dettagli del treno
	}

	@PostMapping("/visualizza/treno")
	public String selezionaTreno(@RequestParam("idTreno") Long idTreno, HttpSession session) {
		// Salva l'ID del treno nella sessione
		session.setAttribute("trenoSelezionato", idTreno);
		// Reindirizza alla pagina che mostra i dettagli del treno senza esporre l'ID
		// nell'URL
		return "redirect:/treni/visualizza/treno";
	}

////////////////////////////// FUNZIONALITA IN PROVA.     

	
	

	@GetMapping("/modifica/aggiungi")
	public String aggiungiVagone(@RequestParam("idTrenoM") Long idTreno, HttpSession session, RedirectAttributes redirectAttributes) {
	    sessione.getUtenteLoggato(session);
	    
	    
	    try {
	        trenoService.aggiungiVagone(idTreno);
	        redirectAttributes.addFlashAttribute("successMessage", "Vagone aggiunto con successo al treno.");
	    } catch (TrenoCreazioneException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Errore durante l'aggiunta del vagone.");
	    }
	    return "redirect:/treni/crea";
	}

	@PostMapping("/modifica/rimuovi")
	public String rimuoviVagone(@RequestParam("idTrenoM") Long idTreno, @RequestParam("idVagone") Long idVagone, HttpSession session, RedirectAttributes redirectAttributes) {
	    sessione.getUtenteLoggato(session);
	    
	    try {
	        trenoService.rimuoviVagone(idTreno, idVagone);
	        redirectAttributes.addFlashAttribute("successMessage", "Vagone rimosso con successo dal treno.");
	    } catch (TrenoCreazioneException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Errore durante la rimozione del vagone.");
	    }
	    return "redirect:/treni/crea";
	}

	@PostMapping("/modifica/copia")
	public String copiaTreno(@RequestParam("idTrenoM") Long idTreno, HttpSession session, RedirectAttributes redirectAttributes) {
	    sessione.getUtenteLoggato(session);
	    try {
	       TrenoDTO nuovoTreno=trenoService.copiaTreno(idTreno);
	        redirectAttributes.addFlashAttribute("nuovoTreno", nuovoTreno);
	        redirectAttributes.addFlashAttribute("successMessage", "Treno copiato con successo.");
	    } catch (TrenoCreazioneException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Errore durante la copia del treno.");
	    }
	    return "redirect:/treni/crea";
	}

	@PostMapping("/modifica/inverti")
	public String invertiTreno(@RequestParam("idTrenoM") Long idTreno, HttpSession session, RedirectAttributes redirectAttributes) {
	    sessione.getUtenteLoggato(session);
	    try {
	        TrenoDTO nuovoTreno = trenoService.invertiVagoni(idTreno);
	        redirectAttributes.addFlashAttribute("nuovoTreno", nuovoTreno);
	        redirectAttributes.addFlashAttribute("successMessage", "Vagoni del treno invertiti con successo.");
	    } catch (TrenoCreazioneException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Errore durante l'inversione dei vagoni.");
	    }
	    return "redirect:/treni/crea";
	}

	@PostMapping("/modifica/cancella")
	public String cancellaTreno(@RequestParam Long idTreno, HttpSession session, RedirectAttributes redirectAttributes) {
	    sessione.getUtenteLoggato(session);
	    try {
	        trenoService.cancellaTreno(idTreno);
	        redirectAttributes.addFlashAttribute("successMessage", "Treno cancellato con successo.");
	    } catch (TrenoCreazioneException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Errore durante la cancellazione del treno.");
	    }
	    return "redirect:/treni/crea";
	}

}
