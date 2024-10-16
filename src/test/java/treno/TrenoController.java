//package com.treno.application.controller;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.treno.application.filter.TrenoFilter;
//import com.treno.application.model.Treno;
//import com.treno.application.model.User;
//import com.treno.application.service.TrenoService;
//
//import jakarta.servlet.http.HttpSession;
//import treno.TrenoDTO;
//
//@Controller
//@RequestMapping("/treni")
//public class TrenoController {
//
//	@Autowired
//	@Qualifier("TrenoService")
//	TrenoService trenoService;
//
//	// Controllo accesso pagina solo in sessione di lgoin
//	@GetMapping
//	public String treni(HttpSession session, Model model) {
//		User utente = (User) session.getAttribute("utente");
//
//		if (utente == null) {
//			return "redirect:/login";
//		}
//
//		model.addAttribute("utente", utente);
//		return "treni"; // dashboard
//	}
//
//// Metodo per mostrare la pagina per creare un nuovo treno
//	@GetMapping("/crea")
//	public String mostraFormCreazioneTreno(HttpSession session, Model model) {
//		User utente = (User) session.getAttribute("utente");
//
//		if (utente == null) {
//			return "redirect:/login";
//		}
//
//		model.addAttribute("treno", Treno.build()); // Se non gli do una istanza giustamente non va .
//		return "crea";
//	}
//
//	// Metodo per gestire la creazione del treno
//	@PostMapping("/crea")
//	public String creaTreno(@RequestParam("input") String input, @RequestParam("marca") String marca,
//			HttpSession session, Model model) {
//		User utente = (User) session.getAttribute("utente");
//
//		if (utente == null) {
//			return "redirect:/login";
//		}
//
//		// Chiama il servizio per creare il treno
//		trenoService.creaTreno(input, utente, marca);
//
//		return "redirect:/treni"; // Reindirizza alla lista dei treni o alla dashboard
//	}
//
//	// Visualizza treni di un utente in base alla sessione di login
//	@GetMapping("/visualizza")
//	public String visualizzaTreniUtente(HttpSession session, Model model) {
//		// Recupera l'utente dalla sessione
//		User utente = (User) session.getAttribute("utente");
//
//		if (utente == null) {
//			return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
//		}
//
//		// Recupera i treni dell'utente dal servizio usando il suo ID
//		List<Treno> treni = trenoService.findAllTreniByUser(utente.getUserId());
//
//		// Mappa ogni Treno su TrenoDTO
//		List<TrenoDTO> treniDto = treni.stream().map(treno -> {
//			// Crea un'istanza vuota di TrenoDto
//			TrenoDTO dto = new TrenoDTO();
//
//			// Imposta i campi di TrenoDto usando i metodi set
//			dto.setIdTreno(treno.getIdTreno());
//			dto.setInVendita(treno.getInVendita());
//			dto.setImmagine(treno.getImmagine());
//
//			// Restituisci l'oggetto dto popolato
//			return dto;
//		}).collect(Collectors.toList());
//
//		// Passa la lista dei DTO alla vista
//		model.addAttribute("treniDto", treniDto);
//		model.addAttribute("utente", utente); // Passa anche l'utente alla vista per eventuali altre informazioni
//		return "visualizzaTreni"; // Restituisce la vista per la lista dei treni
//	}
//
//	///////////////// filtro
//	@GetMapping("/filtro")
//	public String filtraTreni(@ModelAttribute("filtro") TrenoFilter filtro, Model model, HttpSession session) {
//		User utente = (User) session.getAttribute("utente");
//
//		if (utente == null) {
//			return "redirect:/login";
//		}
//
//		long userId = utente.getUserId();
//
//		// Usa il service per filtrare i treni
//		List<Treno> treniFiltrati = trenoService.filtraTreni(filtro, userId);
//
//		// Converti la lista di treni in DTO
//		List<TrenoDTO> treniDto = treniFiltrati.stream().map(treno -> {
//			TrenoDTO dto = new TrenoDTO();
//			dto.setIdTreno(treno.getIdTreno());
//			dto.setImmagine(treno.getImmagine());
//			dto.setMediaValutazioni(treno.getMediaValutazioni());
//			return dto;
//		}).collect(Collectors.toList());
//
//		// Aggiungi la lista filtrata al model per il rendering nella JSP
//		model.addAttribute("treniDto", treniDto);
//		model.addAttribute("filtro", filtro); // Rendi disponibile il filtro corrente nella pagina
//
//		return "visualizzaTreni"; // Il nome della JSP
//	}
//
//	@ModelAttribute("filtro")
//	public TrenoFilter initFilter() {
//		return new TrenoFilter(); // Inizializza un filtro vuoto
//	}
/////////////////////////////////////////////////////////
//
//	@GetMapping("/dettagli/{id}")
//	public String dettagliTreno(@PathVariable Long id, Model model) {
//		Treno treno = trenoService.findById(id); // Recupera il treno dal database
//		model.addAttribute("treno", treno); // Passa il treno alla vista
//		return "dettagliTreno"; // Restituisce la vista
//	}
//
////	    @GetMapping("/dettagli/{id}")
////	    public String dettagliTreno(@PathVariable Long id, Model model) {
////	        TrenoDTO trenoDto = trenoService.findById(id); // Recupera il TrenoDTO dal servizio
////	        if (trenoDto == null) {
////	            return "redirect:/errorPage"; // Se il treno non esiste
////	        }
////	        model.addAttribute("treno", trenoDto); // Aggiunge il treno al model
////	        return "dettagliTreno"; // Restituisce la vista "dettagliTreno.jsp"
////	    }
//
//	@GetMapping("/modifica/{id}")
//	public String modificaTreno(@PathVariable Long id, Model model) {
//		Treno treno = trenoService.findById(id); // Recupera il treno da modificare
//		model.addAttribute("treno", treno); // Passa il treno al model
//		return "modificaTreno"; // Mostra la pagina di modifica
//	}
//
//	@PostMapping("/salvaModifica")
//	public String salvaModifica(@ModelAttribute("treno") Treno treno) {
//		trenoService.update(treno); // Metodo per salvare le modifiche
//		return "redirect:/treni/dettagli/" + treno.getIdTreno(); // Reindirizza ai dettagli del treno modificato
//	}
//
/////////////////////////////////////////////////////////
////	@PostMapping("/dettagli/{id}")
////	public String aggiornaTreno(@PathVariable Long id, @ModelAttribute("treno") Treno trenoAggiornato, Model model) {
////	    Treno trenoEsistente = trenoService.findById(id); // Recupera il treno dal database
////	    if (trenoEsistente == null) {
////	        return "redirect:/errorPage"; // In caso di treno non trovato
////	    }
////
//////	    // Aggiorna i dati del treno esistente con quelli provenienti dal form
//////	    trenoEsistente.setMarca(trenoAggiornato.getMarca());
//////	    trenoEsistente.setPrezzoVendita(trenoAggiornato.getPrezzoVendita());
//////	    trenoEsistente.setPeso(trenoAggiornato.getPeso());
//////	    trenoEsistente.setLunghezza(trenoAggiornato.getLunghezza());
//////	    // Aggiungi altri campi da aggiornare se necessari
//////	    
////	    
////	    trenoEsistente.setInVendita(true);
////	    // Salva le modifiche
////	    trenoService.update(trenoEsistente);
////
////	    // Aggiungi il treno aggiornato al model per visualizzarlo
////	    model.addAttribute("treno", trenoEsistente);
////
////	    // Ritorna alla stessa pagina con i dettagli aggiornati
////	    return "dettagliTreno";
////	}
//
//}
package treno;


